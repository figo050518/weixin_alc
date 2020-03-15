package com.fcgo.weixin.dada.service;

/**
 * DATE: 18/9/3
 *
 * @author: wan
 */
public class BaseServiceContext {


    // 请求uri
    private String url;

    // 业务参数
    private String params;

    public BaseServiceContext(String url, String params){
        this.url = url;
        this.params = params;
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
