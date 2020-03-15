package com.fcgo.weixin.dada.service;

import com.fcgo.weixin.dada.client.DadaApiResponse;
import com.fcgo.weixin.dada.client.DadaRequestClient;
import com.fcgo.weixin.dada.config.AppConfig;
import com.fcgo.weixin.dada.domain.merchant.ShopModel;
import com.fcgo.weixin.dada.domain.order.OrderAddAfterQueryReq;
import com.fcgo.weixin.dada.domain.order.OrderAddModel;
import com.fcgo.weixin.dada.domain.req.DeliverFeeReq;
import com.fcgo.weixin.dada.domain.resp.CityCodeBo;
import com.fcgo.weixin.dada.domain.resp.DeliverFeeResp;
import com.fcgo.weixin.dada.service.merchant.ShopAddContext;
import com.fcgo.weixin.dada.service.merchant.ShopUpdateContext;
import com.fcgo.weixin.dada.service.order.OrderAddAfterQueryContext;
import com.fcgo.weixin.dada.service.order.OrderAddContext;
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

    public DadaApiResponse callAndGetFullResp(BaseServiceContext service){
        logger.info("ProxyService.callAndGetFullResp, api service {},appConfig {}", service, appConfig);
        DadaRequestClient dadaClient = new DadaRequestClient(service, appConfig);
        DadaApiResponse resp = dadaClient.callRpc();
        return resp;
    }

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

    public DadaApiResponse addOrderAfterQuery(OrderAddAfterQueryReq req){
        OrderAddAfterQueryContext context = new OrderAddAfterQueryContext(req);
        return callAndGetFullResp(context);
    }

    public void addShop(ShopModel shopAddModel){
        ShopAddContext context = new ShopAddContext(JSONUtil.toJson(shopAddModel));
        call(context);
    }

    public void updateShop(ShopModel shopAddModel){
        ShopUpdateContext context = new ShopUpdateContext(JSONUtil.toJson(shopAddModel));
        call(context);
    }

    public void addOrder(OrderAddModel orderAddModel){
        OrderAddContext context = new OrderAddContext(JSONUtil.toJson(orderAddModel));
        call(context);
    }
}
