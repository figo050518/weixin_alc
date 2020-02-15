package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.OrderPrepay;

public interface OrderPrepayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderPrepay record);

    int insertSelective(OrderPrepay record);

    OrderPrepay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderPrepay record);

    int updateByPrimaryKey(OrderPrepay record);
}