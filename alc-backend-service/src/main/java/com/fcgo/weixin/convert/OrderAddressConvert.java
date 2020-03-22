package com.fcgo.weixin.convert;

import com.fcgo.weixin.model.backend.bo.OrderAddressBo;
import com.fcgo.weixin.persist.model.OrderAddress;
import org.springframework.beans.BeanUtils;

public class OrderAddressConvert {

    public static final OrderAddressBo do2Bo(OrderAddress orderAddress){
        OrderAddressBo bo = new OrderAddressBo();
        String[] ignoreProps = {"id"};
        BeanUtils.copyProperties(orderAddress, bo, ignoreProps);
        return bo;
    }
}
