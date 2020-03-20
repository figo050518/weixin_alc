package com.fcgo.weixin.dada.service.order;

import com.fcgo.weixin.dada.config.UrlConstant;
import com.fcgo.weixin.dada.domain.order.OrderAddAfterQueryReq;
import com.fcgo.weixin.dada.service.BaseServiceContext;
import lombok.ToString;

@ToString(callSuper = true)
public class OrderAddAfterQueryContext extends BaseServiceContext {

    public OrderAddAfterQueryContext(OrderAddAfterQueryReq orderAddAfterQueryReq) {
        super(UrlConstant.ADD_ORDER_AFTER_QUERY, orderAddAfterQueryReq);
    }


}
