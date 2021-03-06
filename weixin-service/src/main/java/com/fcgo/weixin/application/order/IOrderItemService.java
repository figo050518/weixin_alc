package com.fcgo.weixin.application.order;

import java.util.List;

import com.fcgo.weixin.persist.po.OrderItemPO;

/**
 * 订单子表
 * 
 * @author Ww
 */
public interface IOrderItemService {
    /**
     * 根据订单ID 回去该笔订单所有项
     * 
     * @param orderId
     * @return
     */
    List<OrderItemPO> getByOrderId(int orderId);

    /**
     * 根据主键ID
     * 
     * @param id
     * @return
     */
    OrderItemPO getById(int id);
}
