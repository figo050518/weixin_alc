package com.fcgo.weixin.dada.service.order;

import com.fcgo.weixin.dada.config.UrlConstant;
import com.fcgo.weixin.dada.service.BaseServiceContext;

public class PreQueryDeliverFeeContext extends BaseServiceContext {

    public PreQueryDeliverFeeContext(String params) {
        super(UrlConstant.QUERY_DELIVER_FEE, params);
    }
}
