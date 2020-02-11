package com.fcgo.weixin.application;

import java.util.Map;

/**
 * http client 的接口，支持get和post方法发送http请求
 * 
 * @author guonan 2013-9-16
 */
public interface HttpClientApp {

    /**
     * get方式发送http请求
     * 
     * @param url 请求链接
     * @return 出现异常返回null
     */
    public String get(String url);

    /**
     * post 方式发送http请求
     * 
     * @param url 请求链接
     * @param paramMap 请求参数
     * @return 出现异常返回null
     */
    public String post(String url, Map<String, String> paramMap);

}
