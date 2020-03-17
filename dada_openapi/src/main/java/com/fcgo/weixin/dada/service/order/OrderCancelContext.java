package com.fcgo.weixin.dada.service.order;

import com.fcgo.weixin.dada.config.UrlConstant;
import com.fcgo.weixin.dada.domain.req.OrderCancelReq;
import com.fcgo.weixin.dada.service.BaseServiceContext;

public class OrderCancelContext extends BaseServiceContext {

    public OrderCancelContext(OrderCancelReq req) {
        super(UrlConstant.ORDER_CANCEL, req);
    }
}
