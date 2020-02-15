package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.BrandImg;

public interface BrandImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrandImg record);

    int insertSelective(BrandImg record);

    BrandImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrandImg record);

    int updateByPrimaryKey(BrandImg record);
}