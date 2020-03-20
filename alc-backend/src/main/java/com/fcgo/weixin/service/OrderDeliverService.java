package com.fcgo.weixin.service;

import com.fcgo.weixin.convert.OrderDeliveryConvert;
import com.fcgo.weixin.convert.OrderDeliveryTraceConvert;
import com.fcgo.weixin.model.backend.bo.OrderDeliveryBo;
import com.fcgo.weixin.persist.dao.OrderDeliveryMapper;
import com.fcgo.weixin.persist.dao.OrderDeliveryTraceMapper;
import com.fcgo.weixin.persist.model.OrderDelivery;
import com.fcgo.weixin.persist.model.OrderDeliveryTrace;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderDeliverService {

    @Autowired
    private OrderDeliveryMapper orderDeliveryMapper;

    @Autowired
    private OrderDeliveryTraceMapper orderDeliveryTraceMapper;

    public OrderDeliveryBo getOrderDelivery(String orderCode){
        OrderDelivery sc = new OrderDelivery();
        sc.setOrderCode(orderCode);
        OrderDelivery pod = orderDeliveryMapper.selectByOrderCode(sc);
        if (Objects.isNull(pod)){
            return null;
        }
        OrderDeliveryBo bo = OrderDeliveryConvert.do2Bo(pod);
        //
        OrderDeliveryTrace odtc = OrderDeliveryTrace.builder().orderCode(orderCode)
                .deliveryNum(pod.getDeliveryNum()).build();
        List<OrderDeliveryTrace> odtList = orderDeliveryTraceMapper.selectAllByOrderCodeDeliveryNum(odtc);
        if (CollectionUtils.isNotEmpty(odtList)){
            bo.setOrderDeliveryTraceList(odtList.stream().map(OrderDeliveryTraceConvert::do2Bo).collect(Collectors.toList()));
        }
        return bo;
    }
}
