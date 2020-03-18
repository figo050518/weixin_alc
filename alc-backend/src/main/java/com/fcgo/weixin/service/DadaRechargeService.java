package com.fcgo.weixin.service;

import com.fcgo.weixin.dada.domain.req.BalanceQueryReq;
import com.fcgo.weixin.dada.domain.req.RechargeUrlReq;
import com.fcgo.weixin.dada.domain.resp.BalanceResp;
import com.fcgo.weixin.dada.service.ProxyService;
import com.fcgo.weixin.model.backend.resp.DadaRechargeUrlResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DadaRechargeService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProxyService dadaClient;


    public DadaRechargeUrlResp getDadaRechargeUrl(RechargeUrlReq req){
        String url = dadaClient.queryRechargeUrl(req);
        logger.info("getDadaRechargeUrl url {}",url);
        DadaRechargeUrlResp resp = DadaRechargeUrlResp.builder().url(url).build();
        return resp;
    }


    public BalanceResp getDadaLeftBalance(){
        BalanceQueryReq req = BalanceQueryReq.builder().category(3).build();
        return dadaClient.queryBalance(req);
    }

}
