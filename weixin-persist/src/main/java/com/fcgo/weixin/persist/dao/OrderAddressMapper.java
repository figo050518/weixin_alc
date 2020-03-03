package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.OrderAddress;

public interface OrderAddressMapper {

    int insert(OrderAddress record);

    int insertSelective(OrderAddress record);

    OrderAddress selectByPrimaryKey(Integer id);

    OrderAddress selectByOrderCode(String orderCode);

    int updateByPrimaryKeySelective(OrderAddress record);

    int updateByPrimaryKey(OrderAddress record);
}