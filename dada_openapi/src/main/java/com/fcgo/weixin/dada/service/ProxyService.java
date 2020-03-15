package com.fcgo.weixin.dada.service;

import com.fcgo.weixin.dada.client.DadaApiResponse;
import com.fcgo.weixin.dada.client.DadaRequestClient;
import com.fcgo.weixin.dada.config.AppConfig;
import com.fcgo.weixin.dada.domain.req.DeliverFeeReq;
import com.fcgo.weixin.dada.domain.resp.CityCodeBo;
import com.fcgo.weixin.dada.domain.resp.DeliverFeeResp;
import com.fcgo.weixin.dada.service.order.PreQueryDeliverFeeContext;
import com.fcgo.weixin.dada.utils.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProxyService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AppConfig appConfig;

    public Object call(BaseServiceContext service){
        logger.info("ProxyService.call, api service {},appConfig {}", service, appConfig);
        DadaRequestClient dadaClient = new DadaRequestClient(service, appConfig);
        DadaApiResponse resp = dadaClient.callRpc();
        Object obj = null;
        if (resp.isOk()){
            obj = resp.getResult();
        }
        return obj;
    }

    public DeliverFeeResp queryDeliverFee(DeliverFeeReq dfr){
        String paramsStr = JSONUtil.toJson(dfr);
        PreQueryDeliverFeeContext service = new PreQueryDeliverFeeContext(paramsStr);
        return (DeliverFeeResp)call(service);
    }

    public List<CityCodeBo> getCityCodeList(){
        CityCodeContext ccc = new CityCodeContext("");
        List<CityCodeBo> resp = (List<CityCodeBo>)call(ccc);
        return resp;
    }
}
