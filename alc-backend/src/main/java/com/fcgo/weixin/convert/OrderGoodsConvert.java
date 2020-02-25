package com.fcgo.weixin.convert;

import com.fcgo.weixin.model.backend.bo.OrderGoodsBo;
import com.fcgo.weixin.persist.model.OrderProduct;
import org.springframework.beans.BeanUtils;

public class OrderGoodsConvert {

    public static OrderGoodsBo do2Bo(OrderProduct orderProduct){
        OrderGoodsBo bo = new OrderGoodsBo();
        String[] ignoreProps = {"id","orderId","orderCode"};
        BeanUtils.copyProperties(orderProduct, bo, ignoreProps);
        return bo;
    }
}
