package com.fcgo.weixin.application.impl.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcgo.weixin.application.order.IOrderItemService;
import com.fcgo.weixin.persist.generate.criteria.OrderItemCriteria;
import com.fcgo.weixin.persist.po.OrderItemPO;

@Service
@Transactional
public class OrderItemServiceImpl implements IOrderItemService {

    @Autowired
    private IOrderItemDAO orderItemDAO;

    @Override
    public List<OrderItemPO> getByOrderId(int orderId) {
        OrderItemCriteria orderItemCriteria = new OrderItemCriteria();
        orderItemCriteria.createCriteria().andOrderIdEqualTo(orderId);
        return orderItemDAO.selectByCriteria(orderItemCriteria);
    }

    @Override
    public OrderItemPO getById(int id) {
        return orderItemDAO.selectByPrimaryKey(id);
    }

}
