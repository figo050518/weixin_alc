package com.fcgo.weixin.dada.service;

import com.fcgo.weixin.dada.utils.JSONUtil;
import lombok.ToString;

/**
 * DATE: 18/9/3
 *
 * @author: wan
 */
@ToString
public class BaseServiceContext {


    // 请求uri
    private String url;

    // 业务参数
    private String params;

    public BaseServiceContext(String url, String params){
        this.url = url;
        this.params = params;
    }

    public BaseServiceContext(String url, Object req){
        this.url = url;
        this.params = JSONUtil.toJson(req);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }





}
