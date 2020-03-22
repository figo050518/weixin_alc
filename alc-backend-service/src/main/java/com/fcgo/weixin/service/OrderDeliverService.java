package com.fcgo.weixin.service;

import com.fcgo.weixin.common.exception.ServiceException;
import com.fcgo.weixin.convert.OrderDeliveryConvert;
import com.fcgo.weixin.convert.OrderDeliveryTraceConvert;
import com.fcgo.weixin.dada.domain.order.OrderDetail;
import com.fcgo.weixin.dada.domain.req.OrderDetailReq;
import com.fcgo.weixin.dada.service.ProxyService;
import com.fcgo.weixin.model.backend.bo.OrderDeliveryBo;
import com.fcgo.weixin.model.third.dada.DadaOrderStatus;
import com.fcgo.weixin.persist.dao.OrderDeliveryMapper;
import com.fcgo.weixin.persist.dao.OrderDeliveryTraceMapper;
import com.fcgo.weixin.persist.model.Order;
import com.fcgo.weixin.persist.model.OrderDelivery;
import com.fcgo.weixin.persist.model.OrderDeliveryTrace;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderDeliverService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private OrderDeliveryMapper orderDeliveryMapper;

    @Autowired
    private OrderDeliveryTraceMapper orderDeliveryTraceMapper;

    @Autowired
    private ProxyService dadaClient;

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


    public void checkWhenCancel(Order order){
        //query dada api ,whether cancel
        OrderDetail deliveryOrder = null;
        try{
            OrderDetailReq dadaOrderDetailReq = OrderDetailReq.builder()
                    .order_id(order.getCode()).build();
            deliveryOrder = dadaClient.getOrderDetail(dadaOrderDetailReq);
        }catch (Exception ex){
            logger.warn(" before cancel get dada Order Detail fail, orderCode {}", order.getCode(), ex);
        }
        DadaOrderStatus dadaOrderStatus = null;
        //fetch from local
        OrderDelivery orderDelivery = null;
        if (Objects.isNull(deliveryOrder)){
            OrderDelivery odc = OrderDelivery.builder().orderCode(order.getCode()).build();
            orderDelivery = orderDeliveryMapper.selectByOrderCode(odc);
        }else {
            dadaOrderStatus = DadaOrderStatus.getDadaOrderStatus(deliveryOrder.getStatusCode());
        }
        if (Objects.isNull(orderDelivery)){
            logger.warn("check deliver order before cancel,biz orderCode {} ,not find OrderDelivery in DB", order.getCode());
        }else{
            dadaOrderStatus = DadaOrderStatus.getDadaOrderStatus(orderDelivery.getStatus());
        }

        if (Objects.nonNull(dadaOrderStatus) && !DadaOrderStatus.CACELED.equals(dadaOrderStatus)){
            logger.warn("check deliver order before cancel, orderCode {} ,dada deliver order not cancel, dadaOrderStatus {}", order.getCode(), dadaOrderStatus);
            throw new ServiceException(401, "该订单的达达配送单没有取消，若已取消请稍后重试");
        }
    }
}
