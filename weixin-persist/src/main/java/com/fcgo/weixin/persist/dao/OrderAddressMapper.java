package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.OrderAddress;

public interface OrderAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderAddress record);

    int insertSelective(OrderAddress record);

    OrderAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderAddress record);

    int updateByPrimaryKey(OrderAddress record);
}