package com.fcgo.weixin.convert;

import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.dada.domain.resp.DeliverFeeResp;
import com.fcgo.weixin.model.backend.bo.OrderDeliveryBo;
import com.fcgo.weixin.model.third.dada.DadaOrderStatus;
import com.fcgo.weixin.persist.model.OrderDelivery;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderDeliveryConvert extends BaseConvert{

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

    public static OrderDeliveryBo do2Bo(OrderDelivery od){
        OrderDeliveryBo bo = new OrderDeliveryBo();
        String[] ignoreProps = {"createTime","updateTime"};
        BeanUtils.copyProperties(od, bo, ignoreProps);
        DadaOrderStatus dos = DadaOrderStatus.getDadaOrderStatus(od.getStatus());
        bo.setDeliverOrderCode(od.getDeliveryNum());
        bo.setStatusDesc(Objects.isNull(dos)? null : dos.getDesc());
        bo.setCreateTime(fmtDateFromUnixtime(od.getCreateTime()));
        bo.setUpdateTime(fmtDateFromUnixtime(od.getUpdateTime()));
        return bo;
    }
}
