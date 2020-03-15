package com.fcgo.weixin.service;

import com.fcgo.weixin.dada.domain.req.DeliverFeeReq;
import com.fcgo.weixin.dada.domain.resp.DeliverFeeResp;
import com.fcgo.weixin.dada.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticsService {

    @Autowired
    private ProxyService proxyService;

    public DeliverFeeResp queryDeliverFee(){
        DeliverFeeReq dfr = DeliverFeeReq.builder()
                .build();
        return proxyService.queryDeliverFee(dfr);
    }

    public Object getCityCodeList(){
        return proxyService.getCityCodeList();
    }
}
