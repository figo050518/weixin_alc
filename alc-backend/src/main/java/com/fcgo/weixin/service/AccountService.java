package com.fcgo.weixin.service;

import com.fcgo.weixin.common.exception.ServiceException;
import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.common.util.MD5;
import com.fcgo.weixin.common.util.PageHelper;
import com.fcgo.weixin.convert.AccountConvert;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.AccountBo;
import com.fcgo.weixin.model.backend.constant.AccountConstant;
import com.fcgo.weixin.model.backend.req.AccountListReq;
import com.fcgo.weixin.model.constant.AccountStatus;
import com.fcgo.weixin.model.constant.DelStatus;
import com.fcgo.weixin.persist.dao.AccountMapper;
import com.fcgo.weixin.persist.dao.BrandMapper;
import com.fcgo.weixin.persist.model.Account;
import com.fcgo.weixin.persist.model.Brand;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private BrandMapper brandMapper;

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
            List<Brand> brandList = brandMapper.selectByIds(brandIdSet);
            brandList.forEach(brand -> idBrandMap.put(brand.getId(), brand));
        }
        List<AccountBo> boList = list.stream().map(account -> {
            Brand brand = idBrandMap.get(account.getBrandId());
            AccountBo bo = AccountConvert.do2Bo(account, brand);
            return bo;
        }).collect(Collectors.toList());

        int totalPage = PageHelper.getPageTotal(total, pageSize);
        boBuilder.list(boList).totalPage(totalPage);
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


    public boolean login(HttpSession session,AccountBo bo){
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
        //hit in session
        boolean sessionExists;
        if (sessionExists = Objects.nonNull(session)){
            Integer uidOfSession = (Integer) session.getAttribute(AccountConstant.SESSION_USER_ID_KEY);
            String userNameOfSession = (String) session.getAttribute(AccountConstant.SESSION_USER_NAME_KEY);
            if (Objects.nonNull(uidOfSession) && uidOfSession.equals(uid)
                && Objects.nonNull(userNameOfSession) && userNameOfSession.equals(name)){
                logger.info("login hit user in session req {} uidOfSession {} userNameOfSession {}",bo, uidOfSession, userNameOfSession);
                return true;
            }
        }


        if(sessionExists && pwdMatched){
            session.setAttribute(AccountConstant.SESSION_USER_ID_KEY, uid);
            session.setAttribute(AccountConstant.SESSION_USER_NAME_KEY, name);
            session.setMaxInactiveInterval(3600);
            //
            HttpSession oldSession = userIdSessionCache.get(uid);
            if (Objects.nonNull(oldSession)){
                oldSession.invalidate();
            }
            userIdSessionCache.put(uid, session);
        }
        return true;
    }


}
