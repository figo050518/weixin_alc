package com.fcgo.weixin.dada.client;

import com.fcgo.weixin.dada.config.AppConfig;
import com.fcgo.weixin.dada.config.AppConstant;
import com.fcgo.weixin.dada.service.BaseServiceContext;
import com.fcgo.weixin.dada.utils.EncryptUtil;
import com.fcgo.weixin.dada.utils.HttpClientUtil;
import com.fcgo.weixin.dada.utils.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * DATE: 18/9/3
 *
 * @author: wan
 */
public class DadaRequestClient {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private BaseServiceContext apiService;

    private AppConfig appConfig;

    public DadaRequestClient(BaseServiceContext baseService, AppConfig appConfig) {
        this.apiService = baseService;
        this.appConfig = appConfig;
    }

    public DadaApiResponse callRpc() {
        String requestUrl = this.appConfig.getHost().concat(this.apiService.getUrl());
        String requestParams = this.getRequestParams();

        try {
            String resp = HttpClientUtil.postRequest(requestUrl, requestParams);
            logger.info("call dada api,app config {},apiService {} resp {}", appConfig, apiService, resp);
            return JSONUtil.fromJson(resp, DadaApiResponse.class);
        }catch (Exception e){
            logger.warn("call dada api fail,app config {},apiService {}", appConfig, apiService);
            return DadaApiResponse.except();
        }
    }

    private String getRequestParams() {
        Map<String, String> requestParams = new HashMap<String, String>();
        requestParams.put("source_id", this.appConfig.getSourceId());
        requestParams.put("app_key", this.appConfig.getAppKey());
        requestParams.put("timestamp", String.valueOf(System.currentTimeMillis()));
        requestParams.put("format", AppConstant.FORMAT);
        requestParams.put("v", AppConstant.V);
        requestParams.put("body", this.apiService.getParams());
        requestParams.put("signature", this.getSign(requestParams));
        return JSONUtil.toJson(requestParams);
    }

    private String getSign(Map<String, String> requestParams) {
        //请求参数键值升序排序
        Map<String, String> sortedParams = new TreeMap<String, String>(requestParams);
        Set<Map.Entry<String, String>> entrySets = sortedParams.entrySet();

        //拼参数字符串。
        StringBuilder signStr = new StringBuilder();
        for (Map.Entry<String, String> entry : entrySets) {
            signStr.append(entry.getKey()).append(entry.getValue());
        }

        //MD5签名并校验
        String toSign = this.appConfig.getAppSecret() + signStr.toString() + this.appConfig.getAppSecret();
        String sign = EncryptUtil.encrypt(toSign);
        return sign.toUpperCase();
    }


}
