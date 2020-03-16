package com.fcgo.weixin.dada.service.order;

import com.fcgo.weixin.dada.config.UrlConstant;
import com.fcgo.weixin.dada.service.BaseServiceContext;

public class OrderCancelReasonContext extends BaseServiceContext {

    public OrderCancelReasonContext() {
        super(UrlConstant.CANCEL_REASON_LIST, "");
    }
}
