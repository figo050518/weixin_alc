package com.fcgo.weixin.persist.dao;

import com.fcgo.weixin.persist.model.OrderProduct;

import java.util.List;

public interface OrderProductMapper {



    OrderProduct selectByPrimaryKey(Integer id);

    List<OrderProduct> selectByOrderCode(String orderCode);
}