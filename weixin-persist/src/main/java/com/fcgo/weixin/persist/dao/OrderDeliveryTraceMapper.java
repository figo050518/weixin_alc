package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.OrderDeliveryTrace;

public interface OrderDeliveryTraceMapper {

    int insert(OrderDeliveryTrace record);

    int insertSelective(OrderDeliveryTrace record);

    OrderDeliveryTrace selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderDeliveryTrace record);

    int updateByPrimaryKey(OrderDeliveryTrace record);
}