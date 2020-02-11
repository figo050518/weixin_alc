package com.fcgo.weixin.application.impl.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcgo.weixin.application.order.IOrderRefundImageService;
import com.fcgo.weixin.persist.dao.IOrderRefundImageDAO;
import com.fcgo.weixin.persist.generate.criteria.OrderRefundImageCriteria;
import com.fcgo.weixin.persist.po.OrderRefundImagePO;

@Service
@Transactional
public class OrderRefundImageServiceImpl implements IOrderRefundImageService {
    @Autowired
    private IOrderRefundImageDAO orderRefundImageDAO;

    @Override
    public List<OrderRefundImagePO> getImagesByOrdRefundId(int ordRefundId) {
        OrderRefundImageCriteria orderRefundImageCriteria = new OrderRefundImageCriteria();
        orderRefundImageCriteria.createCriteria().andOrdRefundIdEqualTo(ordRefundId);
        return orderRefundImageDAO.selectByCriteria(orderRefundImageCriteria);
    }

}
