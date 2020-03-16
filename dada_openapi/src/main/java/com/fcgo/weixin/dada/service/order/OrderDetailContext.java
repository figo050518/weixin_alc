package com.fcgo.weixin.dada.service.order;

import com.fcgo.weixin.dada.config.UrlConstant;
import com.fcgo.weixin.dada.domain.req.OrderDetailReq;
import com.fcgo.weixin.dada.service.BaseServiceContext;

public class OrderDetailContext extends BaseServiceContext {

    public OrderDetailContext( OrderDetailReq req) {
        super(UrlConstant.ORDER_DETAIL, req);
    }
}
