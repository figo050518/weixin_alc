package com.fcgo.weixin.service;

import com.fcgo.weixin.common.exception.ServiceException;
import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.common.util.MD5;
import com.fcgo.weixin.common.util.PageHelper;
import com.fcgo.weixin.convert.AccountConvert;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.AccountBo;
import com.fcgo.weixin.model.backend.req.AccountListReq;
import com.fcgo.weixin.model.constant.AccountStatus;
import com.fcgo.weixin.model.constant.DelStatus;
import com.fcgo.weixin.persist.dao.AccountMapper;
import com.fcgo.weixin.persist.model.Account;
import com.fcgo.weixin.persist.model.Brand;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private static Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private BrandService brandService;


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




    public static boolean isAdmin(Integer uid,String userName){
        return Objects.nonNull(uid) && uid == 1
                && StringUtils.isNotBlank(userName) && "admin".equals(userName);
    }




}
