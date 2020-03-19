package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.OrderDelivery;

public interface OrderDeliveryMapper {

    int insert(OrderDelivery record);

    int insertSelective(OrderDelivery record);

    OrderDelivery selectByPrimaryKey(Integer id);

    OrderDelivery selectByOrderCodeDeliverNum(OrderDelivery record);
    OrderDelivery selectByOrderCode(OrderDelivery record);
    int updateByPrimaryKeySelective(OrderDelivery record);

    int updateByPrimaryKey(OrderDelivery record);
}