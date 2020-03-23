package com.fcgo.weixin.convert;

import com.fcgo.weixin.model.backend.bo.OrderDeliveryTraceBo;
import com.fcgo.weixin.model.third.dada.DadaOrderStatus;
import com.fcgo.weixin.persist.model.OrderDeliveryTrace;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

public class OrderDeliveryTraceConvert extends BaseConvert{

    public static OrderDeliveryTraceBo do2Bo(OrderDeliveryTrace odt){
        OrderDeliveryTraceBo bo = new OrderDeliveryTraceBo();
        String[] ignoreProps = {"createTime","dadaUpdateTime"};
        BeanUtils.copyProperties(odt, bo, ignoreProps);
        bo.setDeliveryNum(odt.getClientId());
        DadaOrderStatus dos = DadaOrderStatus.getDadaOrderStatus(odt.getStatus());
        bo.setStatusDesc(Objects.isNull(dos)? null : dos.getDesc());
        bo.setCreateTime(fmtDateFromUnixtime(odt.getCreateTime()));
        bo.setDadaUpdateTime(fmtDateFromUnixtime(odt.getDadaUpdateTime()));
        return bo;
    }
}
