package com.fcgo.weixin.dada.service.recharge;

import com.fcgo.weixin.dada.config.UrlConstant;
import com.fcgo.weixin.dada.domain.req.RechargeUrlReq;
import com.fcgo.weixin.dada.service.BaseServiceContext;

public class RechargeQueryUrlContext extends BaseServiceContext {

    public RechargeQueryUrlContext(RechargeUrlReq req) {
        super(UrlConstant.QUERY_RECHARGE_LINK, req);
    }
}
