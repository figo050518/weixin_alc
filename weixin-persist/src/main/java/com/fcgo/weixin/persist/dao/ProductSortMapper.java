package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.ProductSort;

public interface ProductSortMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductSort record);

    int insertSelective(ProductSort record);

    ProductSort selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductSort record);

    int updateByPrimaryKey(ProductSort record);
}