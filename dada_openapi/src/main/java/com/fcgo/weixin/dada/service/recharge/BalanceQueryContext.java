package com.fcgo.weixin.dada.service.recharge;

import com.fcgo.weixin.dada.config.UrlConstant;
import com.fcgo.weixin.dada.domain.req.BalanceQueryReq;
import com.fcgo.weixin.dada.service.BaseServiceContext;
import lombok.ToString;

@ToString(callSuper = true)
public class BalanceQueryContext extends BaseServiceContext {
    public BalanceQueryContext( BalanceQueryReq req) {
        super(UrlConstant.QUERY_BALANCE, req);
    }
}
