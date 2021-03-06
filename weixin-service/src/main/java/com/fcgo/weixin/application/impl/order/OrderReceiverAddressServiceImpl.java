package com.fcgo.weixin.application.impl.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcgo.weixin.application.order.IOrderReceiverAddressService;
import com.fcgo.weixin.persist.generate.criteria.OrderReceiverAddressCriteria;
import com.fcgo.weixin.persist.po.OrderReceiverAddressPO;

@Service
@Transactional
public class OrderReceiverAddressServiceImpl implements IOrderReceiverAddressService {
    @Autowired
    private IOrderReceiverAddressDAO orderReceiverAddressDAO;

    @Override
    public OrderReceiverAddressPO getByOrderId(int orderId) {
        OrderReceiverAddressCriteria orderReceiverAddressCriteria = new OrderReceiverAddressCriteria();
        orderReceiverAddressCriteria.createCriteria().andOrderIdEqualTo(orderId);
        OrderReceiverAddressPO orderReceiverAddressPO = new OrderReceiverAddressPO();
        List<OrderReceiverAddressPO> receiverAddressPOs =
                orderReceiverAddressDAO.selectByCriteria(orderReceiverAddressCriteria);
        if (receiverAddressPOs != null && receiverAddressPOs.size() > 0) {
            orderReceiverAddressPO = receiverAddressPOs.get(0);
        }
        return orderReceiverAddressPO;
    }

}
