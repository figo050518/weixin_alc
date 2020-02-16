package com.fcgo.weixin.service;

import com.fcgo.weixin.common.exception.ServiceException;
import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.common.util.PageHelper;
import com.fcgo.weixin.convert.AccountConvert;
import com.fcgo.weixin.model.PageResponseBO;
import com.fcgo.weixin.model.backend.bo.AccountBo;
import com.fcgo.weixin.model.backend.req.AccountListReq;
import com.fcgo.weixin.model.constant.AccountStatus;
import com.fcgo.weixin.persist.dao.AccountMapper;
import com.fcgo.weixin.persist.dao.BrandMapper;
import com.fcgo.weixin.persist.model.Account;
import com.fcgo.weixin.persist.model.Brand;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountMapper accountMapper;


    @Autowired
    private BrandMapper brandMapper;

    public int add(AccountBo bo){
        logger.info("in add account, {}", bo);
        Account condition = AccountConvert.bo2Do(bo);
        int currentDT = DateUtil.getCurrentTimeSeconds();
        condition.setStatus(AccountStatus.USEFUL.getCode());
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




}
