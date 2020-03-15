package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.BrandWalletBills;

public interface BrandWalletBillsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrandWalletBills record);

    int insertSelective(BrandWalletBills record);

    BrandWalletBills selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrandWalletBills record);

    int updateByPrimaryKey(BrandWalletBills record);
}