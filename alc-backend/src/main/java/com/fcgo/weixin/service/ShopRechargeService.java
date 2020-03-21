package com.fcgo.weixin.service;

import com.alibaba.fastjson.JSONObject;
import com.fcgo.weixin.common.exception.SessionExpireException;
import com.fcgo.weixin.common.util.BigDecimalHelper;
import com.fcgo.weixin.common.util.SHA256;
import com.fcgo.weixin.dada.domain.req.RechargeUrlReq;
import com.fcgo.weixin.httpclient.RestTemplateUtils;
import com.fcgo.weixin.model.backend.bo.RechargeOrderBo;
import com.fcgo.weixin.model.backend.resp.LoginUserResp;
import com.fcgo.weixin.persist.dao.RechargeOrderMapper;
import com.fcgo.weixin.persist.model.RechargeOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class ShopRechargeService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String RECHARGE_API = "/order/api/payUrlForBackend";

    @Autowired
    private LoginService loginService;
    @Autowired
    @Qualifier("service-restTemplate")
    private RestTemplate restTemplate;


    @Value("${alc.service.order.url}")
    private String orderServiceUrl;

    @Autowired
    private RechargeOrderMapper rechargeOrderMapper;


    public JSONObject getRechargeUrl(RechargeUrlReq req) throws SessionExpireException {
        //TODO create shop wallet if not present
        Double amountDouble = req.getAmount();
        if (Objects.isNull(amountDouble)){
            logger.warn("getRechargeUrl fail,amount wrong,req {}", req);
        }

        LoginUserResp loginUserResp = loginService.getLoginUser();
        if (Objects.isNull(loginUserResp)){
            throw new SessionExpireException();
        }
        String amount = BigDecimalHelper.format(amountDouble);
        logger.info("getRechargeUrl user {} ,req {}, format amount {}", loginUserResp, req, amount);
        String url = getRechargeApiUrl();
        Map<String,Object> params = new HashMap<>(3);
        params.put("amount", amount);
        params.put("brandId", loginUserResp.getBrandId());
        params.put("accessCode", SHA256.getAccessCode());

        JSONObject result = null;
        try {
            JSONObject resp = RestTemplateUtils.post(restTemplate, url, params, JSONObject.class);
            Integer businessCode = resp.getInteger("businessCode");
            logger.info("in getRechargeUrl,api url {} params {} resp {}", url, params, resp);
            if (Objects.nonNull(businessCode)&& businessCode.equals(100)){
                result = resp.getJSONObject("returnData");
            }
        } catch (Exception e) {
            logger.warn("call order getRechargeUrl fail, url {} params {}", url, params, e);
        }finally {
            return result;
        }

    }

    public String getRechargeApiUrl(){
        return new StringBuilder(orderServiceUrl).append(RECHARGE_API).toString();
    }


    public RechargeOrderBo queryRechargeOrder(RechargeOrderBo req) throws SessionExpireException {
        LoginUserResp loginUserResp = loginService.getLoginUser();
        if (Objects.isNull(loginUserResp)){
            throw new SessionExpireException();
        }

        RechargeOrder condition = RechargeOrder.builder()
                .brandId(loginUserResp.getBrandId())
                .orderCode(req.getOrderCode()).build();

        RechargeOrder pro = rechargeOrderMapper.selectByOrderCode(condition);
        if (Objects.isNull(pro)){
            logger.warn("queryRechargeOrder not find RechargeOrder , req {}", req);
            return req;
        }
        return RechargeOrderBo.builder().status(pro.getStatus()).build();
    }

}
