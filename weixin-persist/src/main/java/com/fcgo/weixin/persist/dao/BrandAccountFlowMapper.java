package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.BrandAccountFlow;

public interface BrandAccountFlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrandAccountFlow record);

    int insertSelective(BrandAccountFlow record);

    BrandAccountFlow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrandAccountFlow record);

    int updateByPrimaryKey(BrandAccountFlow record);
}