package com.fcgo.weixin.convert;

import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.dada.domain.resp.DeliverFeeResp;
import com.fcgo.weixin.model.third.dada.DadaOrderStatus;
import com.fcgo.weixin.persist.model.OrderDelivery;

import java.math.BigDecimal;

public class OrderDeliveryConvert {

    public static OrderDelivery buildDOOfInsert(String orderCode,
                                                String deliveryNo,
                                                DeliverFeeResp deliverFeeResp){
        int cdt = DateUtil.getCurrentTimeSeconds();
        OrderDelivery odCreateCondition = OrderDelivery.builder()
                .orderCode(orderCode)
                .fee(new BigDecimal(deliverFeeResp.getFee()))
                .deliverFee(new BigDecimal(deliverFeeResp.getDeliverFee()))
                .deliveryNum(deliveryNo)
                .status(DadaOrderStatus.WAITING_ACCEPT.getCode())
                .createTime(cdt)
                .updateTime(cdt)
                .build();
        return odCreateCondition;
    }
}
