package com.fcgo.weixin.application.weixin;

import java.util.Map;

import com.fcgo.weixin.application.dto.PayRequest;

public interface WeixinPayService {
    public Map<String, String> jsApiPay(PayRequest payRequest, String openid);

    public boolean refund(PayRequest payRequest);

    public boolean hasTranactionLog(String transactionId);

    public boolean hasRefundTranactionLog(String transactionId);

}
