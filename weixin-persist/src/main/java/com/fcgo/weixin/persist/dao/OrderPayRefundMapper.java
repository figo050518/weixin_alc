package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.OrderPayRefund;

public interface OrderPayRefundMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderPayRefund record);

    int insertSelective(OrderPayRefund record);

    OrderPayRefund selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderPayRefund record);

    int updateByPrimaryKey(OrderPayRefund record);
}