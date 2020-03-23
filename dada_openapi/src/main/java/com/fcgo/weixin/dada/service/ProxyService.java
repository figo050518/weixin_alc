package com.fcgo.weixin.dada.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fcgo.weixin.dada.client.DadaApiResponse;
import com.fcgo.weixin.dada.client.DadaRequestClient;
import com.fcgo.weixin.dada.config.AppConfig;
import com.fcgo.weixin.dada.domain.merchant.ShopModel;
import com.fcgo.weixin.dada.domain.order.OrderAddAfterQueryReq;
import com.fcgo.weixin.dada.domain.order.OrderAddModel;
import com.fcgo.weixin.dada.domain.order.OrderDetail;
import com.fcgo.weixin.dada.domain.req.BalanceQueryReq;
import com.fcgo.weixin.dada.domain.req.DeliverFeeReq;
import com.fcgo.weixin.dada.domain.req.OrderDetailReq;
import com.fcgo.weixin.dada.domain.req.RechargeUrlReq;
import com.fcgo.weixin.dada.domain.resp.BalanceResp;
import com.fcgo.weixin.dada.domain.req.OrderCancelReq;
import com.fcgo.weixin.dada.domain.resp.CityCodeBo;
import com.fcgo.weixin.dada.domain.resp.DeliverFeeResp;
import com.fcgo.weixin.dada.domain.resp.OrderCancelReason;
import com.fcgo.weixin.dada.domain.resp.OrderCancelResp;
import com.fcgo.weixin.dada.service.merchant.ShopAddContext;
import com.fcgo.weixin.dada.service.merchant.ShopUpdateContext;
import com.fcgo.weixin.dada.service.order.*;
import com.fcgo.weixin.dada.service.recharge.BalanceQueryContext;
import com.fcgo.weixin.dada.service.recharge.RechargeQueryUrlContext;
import com.fcgo.weixin.dada.service.order.OrderAddAfterQueryContext;
import com.fcgo.weixin.dada.service.order.OrderAddContext;
import com.fcgo.weixin.dada.service.order.OrderCancelContext;
import com.fcgo.weixin.dada.service.order.PreQueryDeliverFeeContext;
import com.fcgo.weixin.dada.utils.JSONUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ProxyService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AppConfig appConfig;

    public boolean checkSign(Map<String,String> params,String sign){
        DadaRequestClient dadaClient = new DadaRequestClient(null, appConfig);
        String signInLocal = dadaClient.getSign(params);
        logger.info("check sign, sign from In-param {},sign by calculate in time {} ", sign, signInLocal);
        return StringUtils.isNotBlank(sign) && StringUtils.isNotBlank(signInLocal) && sign.equals(signInLocal);
    }

    public DadaApiResponse callAndGetFullResp(BaseServiceContext service){
        logger.info("ProxyService.callAndGetFullResp, api service {},appConfig {}", service, appConfig);
        DadaRequestClient dadaClient = new DadaRequestClient(service, appConfig);
        DadaApiResponse resp = dadaClient.callRpc();
        return resp;
    }

    public Object call(BaseServiceContext service, Class clazz){
        logger.info("ProxyService.call, api service {},appConfig {}", service, appConfig);
        DadaRequestClient dadaClient = new DadaRequestClient(service, appConfig);
        DadaApiResponse resp = dadaClient.callRpc();
        Object obj = null;
        if (resp.isOk()){
            obj = resp.getResult();

            if (obj instanceof Collection){
                //JSONObject.parseObject(JSONObject.toJSONString(obj), new TypeReference<Collection>(){});
                return obj;
            }

            if (obj instanceof JSONObject && Objects.nonNull(clazz)){
                return JSONObject.parseObject(((JSONObject) obj).toJSONString(),clazz);
            }
        }
        return obj;
    }

    public DeliverFeeResp queryDeliverFee(DeliverFeeReq dfr){
        String paramsStr = JSONUtil.toJson(dfr);
        PreQueryDeliverFeeContext service = new PreQueryDeliverFeeContext(paramsStr);
        return (DeliverFeeResp)call(service, DeliverFeeResp.class);
    }

    public List<CityCodeBo> getCityCodeList(){
        CityCodeContext ccc = new CityCodeContext("");
        List<CityCodeBo> resp = (List<CityCodeBo>)call(ccc, List.class);
        return resp;
    }

    public DadaApiResponse addOrderAfterQuery(OrderAddAfterQueryReq req){
        OrderAddAfterQueryContext context = new OrderAddAfterQueryContext(req);
        return callAndGetFullResp(context);
    }

    public OrderDetail getOrderDetail(OrderDetailReq req){
        OrderDetailContext context = new OrderDetailContext(req);
        return (OrderDetail)call(context, OrderDetail.class);
    }

    public OrderCancelResp cancelOrder(OrderCancelReq req){
        OrderCancelContext context = new OrderCancelContext(req);
        return (OrderCancelResp)call(context, OrderCancelResp.class);
    }


    public void addShop(List<ShopModel> shopModelList){
        ShopAddContext context = new ShopAddContext(JSONUtil.toJson(shopModelList));
        call(context, null);
    }

    public void updateShop(ShopModel shopAddModel){
        ShopUpdateContext context = new ShopUpdateContext(JSONUtil.toJson(shopAddModel));
        call(context, null);
    }

    public void addOrder(OrderAddModel orderAddModel){
        OrderAddContext context = new OrderAddContext(JSONUtil.toJson(orderAddModel));
        call(context, null);
    }

    /**
     * 调用取消订单接口时，需要传递取消原因ID，通过此接口获取订单取消理由列表
     * {@code http://newopen.imdada.cn/#/development/file/reasonList}
     * @return
     */
    public List<OrderCancelReason> getOrderCancelReasonList(){
        OrderCancelReasonContext context = new OrderCancelReasonContext();
        return (List<OrderCancelReason>)call(context, null);
    }

    /**
     * 生成充值的链接
     * @param req
     * @return
     */
    public String queryRechargeUrl(RechargeUrlReq req){
        RechargeQueryUrlContext context = new RechargeQueryUrlContext(req);
        return (String)call(context, String.class);
    }

    /**
     * 查询运费账户或红包账户的余额
     * @param req
     * @return
     */
    public BalanceResp queryBalance(BalanceQueryReq req){
        BalanceQueryContext context = new BalanceQueryContext(req);
        return (BalanceResp)call(context, BalanceResp.class);
    }
}
