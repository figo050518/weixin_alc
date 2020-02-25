package com.fcgo.weixin.service;

import com.alibaba.fastjson.JSONObject;
import com.fcgo.weixin.common.constants.HeadKey;
import com.fcgo.weixin.common.exception.ServiceException;
import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.common.util.MD5;
import com.fcgo.weixin.common.util.PageHelper;
import com.fcgo.weixin.convert.AccountConvert;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.AccountBo;
import com.fcgo.weixin.model.backend.constant.AccountConstant;
import com.fcgo.weixin.model.backend.req.AccountListReq;
import com.fcgo.weixin.model.backend.resp.LoginUserResp;
import com.fcgo.weixin.model.constant.AccountStatus;
import com.fcgo.weixin.model.constant.DelStatus;
import com.fcgo.weixin.persist.dao.AccountMapper;
import com.fcgo.weixin.persist.model.Account;
import com.fcgo.weixin.persist.model.Brand;
import com.fcgo.weixin.util.LoggerManager;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private static Logger loginLog = LoggerManager.getLoginLog();

    private static Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private BrandService brandService;

    private static final Map<String,HttpSession> sidSessionCache = new HashMap<>(16);

    private static final Map<Integer,HttpSession> userIdSessionCache = new HashMap<>(16);

    public int add(AccountBo bo){
        logger.info("in add account, {}", bo);
        String name;
        if (StringUtils.isBlank(name = bo.getName()) ){
            throw new ServiceException(400,"用户名不能为空");
        }
        String pwd;
        if(StringUtils.isBlank(pwd=bo.getPwd())){
            throw new ServiceException(400,"密码不能为空");
        }
        name = name.trim();
        Account pa = accountMapper.selectByName(name);
        if (Objects.nonNull(pa)){
            throw new ServiceException(400,"用户名已存在");
        }
        pwd = pwd.trim();
        Account condition = AccountConvert.bo2Do(bo);
        String pwdAfterEncrypt = MD5.md5(pwd);
        condition.setPwd(pwdAfterEncrypt);
        int currentDT = DateUtil.getCurrentTimeSeconds();
        condition.setStatus(Objects.isNull(bo.getStatus()) ? AccountStatus.USEFUL.getCode() : bo.getStatus());
        condition.setCreateTime(currentDT);
        condition.setUpdateTime(currentDT);
        int rows = accountMapper.insert(condition);
        return rows;
    }

    public int update(AccountBo bo){
        logger.info("in update account, {}", bo);
        if (Objects.isNull(bo.getId())){
            throw new ServiceException(400, "ID不存在");
        }
        Account condition = AccountConvert.bo2Do(bo);
        String pwd = bo.getPwd();
        if (StringUtils.isNotBlank(pwd)){
            //
            pwd = pwd.trim();
            String pwdAfterEncrypt = MD5.md5(pwd);
            logger.info("update account pwd not blank,{},pwdAfterEncrypt {}",bo, pwdAfterEncrypt);
            condition.setPwd(pwdAfterEncrypt);
        }
        int currentDT = DateUtil.getCurrentTimeSeconds();
        condition.setUpdateTime(currentDT);
        int rows = accountMapper.updateByPrimaryKeySelective(condition);
        return rows;
    }

    public PageResponseBO getList(AccountListReq req){
        int page = req.getPage();
        int pageSize = req.getSize();

        PageResponseBO.PageResponseBOBuilder boBuilder = PageResponseBO.builder()
                .currentPage(page)
                .pageSize(pageSize);
        Account condition = null;
        int total = accountMapper.selectCntByCondition(condition);

        if (total==0){
            return boBuilder.build();
        }
        int offset = PageHelper.getOffsetOfMysql(page,pageSize);
        List<Account> list = accountMapper.selectAllByCondtion(condition,offset,pageSize );
        Set<Integer> brandIdSet = list.stream().map(Account::getBrandId).collect(Collectors.toSet());
        final Map<Integer,Brand> idBrandMap = new HashMap<>(list.size());
        if (CollectionUtils.isNotEmpty(brandIdSet)) {
            idBrandMap.putAll(brandService.getIdBrandMap(brandIdSet));
        }
        List<AccountBo> boList = list.stream().map(account -> {
            Brand brand = idBrandMap.get(account.getBrandId());
            AccountBo bo = AccountConvert.do2Bo(account, brand);
            return bo;
        }).collect(Collectors.toList());

        int totalPage = PageHelper.getPageTotal(total, pageSize);
        boBuilder.list(boList).total(total).totalPage(totalPage);
        return boBuilder.build();
    }

    public int delete(Integer uid){
        if (Objects.isNull(uid) || uid<1){
            throw new ServiceException(401, "UID不合法");
        }
        Account condition = Account.builder().id(uid).isDel(DelStatus.YES.getCode()).build();
        logger.info("delete account {}", condition);
        return accountMapper.updateByPrimaryKeySelective(condition);
    }


    public LoginUserResp login(HttpServletRequest request, AccountBo bo){
        loginLog.info("login {}", bo);
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
            loginLog.warn("login user not exist {}", bo);
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
            loginLog.warn("login pwd not match req {} pwdAfterEncrypt {} pwdInDB {}", bo, pwdAfterEncrypt, pwdInDB);
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
                loginLog.info("login hit user in session req {} user {} ",bo, resp);
                return resp;
            }
        }
        //no session, first login

        if(pwdMatched){
            //create session
            session = request.getSession();
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
            HttpSession oldSession = userIdSessionCache.get(uid);
            if (Objects.nonNull(oldSession)){
                String sessionId = oldSession.getId();
                loginLog.info("find old session, do invalidate {}, sessionId {}",bo, sessionId);
                sidSessionCache.remove(sessionId);
            }
            userIdSessionCache.put(uid, session);
            sidSessionCache.put(session.getId(), session);
        }
        return resp;
    }

    public boolean logout(HttpSession session, AccountBo bo){
        String name = bo.getName();
        if (StringUtils.isBlank(name)){
            throw new ServiceException(401,"用户名不能为空");
        }
        name = name.trim();
        //get from DB
        Account account = accountMapper.selectByName(name);
        if (Objects.isNull(account)){
            loginLog.warn("login user not exist {}", bo);
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
            HttpSession oldSession = userIdSessionCache.get(uid);
            if (Objects.nonNull(oldSession)){
                String sessionId = oldSession.getId();
                loginLog.info("log out,find old session, do invalidate {}, sessionId {}",bo, sessionId);
                oldSession.invalidate();
            }
            session.invalidate();
            userIdSessionCache.remove(uid);
            sidSessionCache.remove(session.getId());
            return true;
        }
        return false;
    }

    public static boolean isLoginBySession(String sessionId){
        return sidSessionCache.containsKey(sessionId);
    }

    public LoginUserResp getLoginUser(Integer uid){
        HttpSession session = userIdSessionCache.get(uid);


        LoginUserResp resp = convertFromSession(session);
        return resp;
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

    public boolean isAdmin(Integer uid,String userName){
        return Objects.nonNull(uid) && uid == 1
                && StringUtils.isNotBlank(userName) && "admin".equals(userName);
    }

    public LoginUserResp getLoginUser(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String sessionKey = request.getHeader(HeadKey.token);
        HttpSession session =  sidSessionCache.get(sessionKey);
        LoginUserResp resp = convertFromSession(session);
        logger.info("get login user {}", resp);
        return resp;
    }

}
