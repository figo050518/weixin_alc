package com.fcgo.weixin.service;

import com.alibaba.fastjson.JSONObject;
import com.fcgo.weixin.common.constants.HeadKey;
import com.fcgo.weixin.common.exception.ServiceException;
import com.fcgo.weixin.common.exception.SessionExpireException;
import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.common.util.MD5;
import com.fcgo.weixin.model.backend.bo.AccountBo;
import com.fcgo.weixin.model.backend.constant.AccountConstant;
import com.fcgo.weixin.model.backend.resp.LoginUserResp;
import com.fcgo.weixin.model.constant.AccountStatus;
import com.fcgo.weixin.persist.dao.AccountLoginRecordMapper;
import com.fcgo.weixin.persist.dao.AccountMapper;
import com.fcgo.weixin.persist.model.Account;
import com.fcgo.weixin.persist.model.AccountLoginRecord;
import com.fcgo.weixin.util.LoggerManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginService {

    private static final Logger logger = LoggerManager.getLoginLog();

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountLoginRecordMapper accountLoginRecordMapper;

    private static final Map<String,LoginUserResp> sidSessionCache = new HashMap<>(16);

    private static final Map<Integer,LoginUserResp> userIdSessionCache = new HashMap<>(16);


    public boolean isLogin(String sessionId) throws SessionExpireException {
        logger.info("check isLogin {}", sessionId);
        LoginUserResp loginUserResp = getLoginUser();
        return Objects.nonNull(loginUserResp);
    }

    public LoginUserResp login(HttpServletRequest request, AccountBo bo){
        logger.info("login {}", bo);
        String name = bo.getName();
        if (StringUtils.isBlank(name)){
            throw new ServiceException(401,"用户名不能为空");
        }
        String pwd = bo.getPwd();
        if (StringUtils.isBlank(pwd)){
            throw new ServiceException(401,"密码不能为空");
        }
        name = name.trim();
        pwd = pwd.trim();
        //get from DB
        Account account = accountMapper.selectByName(name);
        if (Objects.isNull(account)){
            logger.warn("login user not exist {}", bo);
            throw new ServiceException(401,"用户名或密码错误");
        }
        //check status
        boolean illegal = account.getStatus().equals(AccountStatus.USELESS.getCode());
        if (illegal){
            throw new ServiceException(401, "用户失效");
        }
        String pwdInDB = account.getPwd();
        String pwdAfterEncrypt = MD5.md5(pwd);
        boolean pwdMatched = pwdAfterEncrypt.equals(pwdInDB);
        if (!pwdMatched){
            logger.warn("login pwd not match req {} pwdAfterEncrypt {} pwdInDB {}", bo, pwdAfterEncrypt, pwdInDB);
            throw new ServiceException(401,"用户名或密码错误");
        }
        final Integer uid = account.getId();
        LoginUserResp resp = null;
        //hit in session
        HttpSession session = request.getSession(false);
        boolean sessionExists;
        if (sessionExists = Objects.nonNull(session)){
            String userInfo = (String)session.getAttribute(AccountConstant.SESSION_USER_INFO_KEY);
            if (StringUtils.isNotBlank(userInfo)){
                resp = JSONObject.parseObject(userInfo, LoginUserResp.class);
                logger.info("login hit user in session req {} user {} ",bo, resp);
                return resp;
            }
        }
        //no session, first login
        if(pwdMatched){
            //create session
            session = request.getSession();
            final int expired = 3600;
            int currentTime = DateUtil.getCurrentTimeSeconds();
            AccountLoginRecord insertCondition = AccountLoginRecord.builder()
                    .uid(uid).expired(expired)
                    .startTime(currentTime)
                    .expiredTime(currentTime+expired)
                    .sessionKey(session.getId())
                    .build();
            //使用了replace
            int rows = accountLoginRecordMapper.insert(insertCondition);
            if (rows<1){
                logger.warn("login insert into DB fail, {}", bo);
                session.invalidate();
                throw new ServiceException(501, "系统出错，请稍后重试");
            }

            resp = LoginUserResp.builder()
                    .uid(uid)
                    .brandId(account.getBrandId())
                    .userName(name)
                    .sessionKey(session.getId())
                    .build();
            String userInfo = JSONObject.toJSONString(resp);
            session.setAttribute(AccountConstant.SESSION_USER_INFO_KEY, userInfo);
            session.setMaxInactiveInterval(3600);
            //
            LoginUserResp oldSession = userIdSessionCache.get(uid);
            if (Objects.nonNull(oldSession)){
                String sessionId = oldSession.getSessionKey();
                logger.info("find old session, do invalidate {}, sessionId {}",bo, sessionId);
                sidSessionCache.remove(sessionId);
            }
            userIdSessionCache.put(uid, resp);
            sidSessionCache.put(session.getId(), resp);
        }
        return resp;
    }

    public boolean logout(HttpSession session) throws SessionExpireException {
        LoginUserResp loginUserResp = getLoginUser();
        String name = loginUserResp.getUserName();
        //get from DB
        Account account = accountMapper.selectByName(name);
        if (Objects.isNull(account)){
            logger.warn("login user not exist {}", loginUserResp);
            throw new ServiceException(401,"用户名错误");
        }
        //check status
        boolean illegal = account.getStatus().equals(AccountStatus.USELESS.getCode());
        if (illegal){
            throw new ServiceException(401, "用户失效");
        }

        final Integer uid = account.getId();
        //hit in session
        boolean sessionExists = Objects.nonNull(session);
        if(sessionExists){
            //
            AccountLoginRecord condition = new AccountLoginRecord();
            condition.setUid(uid);
            condition.setExpiredTime(DateUtil.getCurrentTimeSeconds());
            int result = accountLoginRecordMapper.updateByPrimaryKeySelective(condition);
            if (result<1){
                logger.warn("logout fail {}", loginUserResp);
                throw new ServiceException(401,"登出失败");
            }
            LoginUserResp oldSession = userIdSessionCache.get(uid);
            if (Objects.nonNull(oldSession)){
                String sessionIdOfOld = oldSession.getSessionKey();
                logger.info("log out,find old logUser,do remove old sessionId {}, current {}", sessionIdOfOld, loginUserResp);
            }
            session.invalidate();
            userIdSessionCache.remove(uid);
            sidSessionCache.remove(session.getId());
            return true;
        }
        return false;
    }


    private static LoginUserResp convertFromSession(HttpSession session){
        if (Objects.isNull(session)){
            return null;
        }
        String userInfoStr = (String) session.getAttribute(AccountConstant.SESSION_USER_INFO_KEY);
        logger.info("getLoginUser from session {}", userInfoStr);
        LoginUserResp resp = JSONObject.parseObject(userInfoStr, LoginUserResp.class);
        return resp;
    }

    public LoginUserResp getLoginUser() throws SessionExpireException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String sessionKey = request.getHeader(HeadKey.token);
        if (StringUtils.isBlank(sessionKey)){
            logger.warn("getLoginUser fail,miss head ,url {}",request.getRequestURL());
            throw new SessionExpireException();
        }

        LoginUserResp respInCache = sidSessionCache.get(sessionKey);
        if (Objects.nonNull(respInCache)){
            logger.info("get login user from local cache {}- {}", sessionKey, respInCache);
            return respInCache;
        }
        HttpSession session = request.getSession(false);
        if (Objects.isNull(session)){
            logger.warn("getLoginUser fail,miss session ,session id {}",sessionKey);
        }
        LoginUserResp resp = convertFromSession(session);
        if (Objects.nonNull(resp)){
            logger.info("get user from session, {}", resp);
            return resp;
        }
        logger.warn("get login user ,not find in session {}", sessionKey);
        AccountLoginRecord accountLoginRecord = accountLoginRecordMapper.selectBySessionKey(sessionKey);
        if (Objects.isNull(accountLoginRecord)){
            logger.warn("get login user ,not find in DB {}", sessionKey);
            throw new SessionExpireException();
        }
        logger.info("get login user from DB, {}", accountLoginRecord);
        int currentTime = DateUtil.getCurrentTimeSeconds();
        if (accountLoginRecord.getExpiredTime()<currentTime){
            logger.info("get login user from DB,but expired {}, currentTime {}", accountLoginRecord, currentTime);
            throw new SessionExpireException();
        }
        Account account = accountMapper.selectByPrimaryKey(accountLoginRecord.getUid());
        resp = LoginUserResp.builder()
                .uid(accountLoginRecord.getUid())
                .userName(account.getName())
                .brandId(account.getBrandId())
                .sessionKey(sessionKey)
                .build();
        logger.info("finally get login user  {}",  resp);
        return resp;
    }


}
