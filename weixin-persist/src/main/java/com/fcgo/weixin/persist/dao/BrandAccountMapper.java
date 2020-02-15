package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.BrandAccount;

public interface BrandAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrandAccount record);

    int insertSelective(BrandAccount record);

    BrandAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrandAccount record);

    int updateByPrimaryKey(BrandAccount record);
}