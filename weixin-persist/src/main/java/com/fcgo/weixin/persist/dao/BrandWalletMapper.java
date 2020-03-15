package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.BrandWallet;

public interface BrandWalletMapper {
    BrandWallet selectByBrandId(Integer brandId);

    int insert(BrandWallet record);

    int insertSelective(BrandWallet record);

    BrandWallet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrandWallet record);

    int updateByPrimaryKey(BrandWallet record);
}