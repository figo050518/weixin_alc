package com.fcgo.weixin.httpclient;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * RestTemplate wrapper
 * Created by chenchao
 */
public class RestTemplateUtils {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    public static final String FORM_WITH_UTF8 = "application/x-www-form-urlencoded;charset=utf-8";
    protected static final Logger log = LoggerFactory.getLogger(RestTemplateUtils.class);

    /**
     * HTTP POST
     *
     * @param restTemplate restTemplate
     * @param path         请求路径
     * @param request      请求消息体
     * @param responseType 响应类型
     * @param <T>          响应类型
     * @param extraHeaders 额外的请求头
     * @return 响应结果
     * @throws Exception
     */
    public static <T> T post(RestTemplate restTemplate,
                             String path,
                             Object request,
                             Class<T> responseType,
                             Map<String, String> extraHeaders) throws Exception {

        log.debug("begin to do http post. path:{}, request :{}", path, request);

        MultiValueMap<String, String> headers = setupPostHeaders(request, extraHeaders);
        HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);
        T t = restTemplate.postForObject(path, requestEntity, responseType);

        log.debug("end to do http get. path:{}, request params:{}. response body: {}", path, request, t);

        return t;
    }


    public static <T> T post(AsyncRestTemplate restTemplate,
                             String path, Object request,
                             Class<T> responseType,
                             Map<String, String> extraHeaders) throws Exception {

        log.debug("begin to do http post. path:{}, request :{}", path, request);

        MultiValueMap<String, String> headers = setupPostHeaders(request, extraHeaders);
        HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);
        ListenableFuture<ResponseEntity<T>> t = restTemplate.postForEntity(path, requestEntity, responseType);
        log.debug("end to do http get. path:{}, request params:{}. response body: {}", path, request, t);
        return t.get().getBody();
    }

    /**
     *  设置post的请求头。 如果request是MultiValueMap，则使用form方式提交，并且设置content-type为utf8
     * @param request 请求
     * @param extraHeaders 请求头
     * @return post头
     */
    private static MultiValueMap<String, String> setupPostHeaders(Object request, Map<String, String> extraHeaders) {
        MultiValueMap<String, String> headers = convert(extraHeaders);
        if (request != null && MultiValueMap.class.isAssignableFrom(request.getClass())) {
            headers.set("Content-Type", FORM_WITH_UTF8);
        }
        return headers;
    }

    /**
     * HTTP POST
     *
     * @param restTemplate restTemplate
     * @param path         请求路径
     * @param request      请求消息体
     * @param responseType 响应类型
     * @param <T>          响应类型
     * @return 响应结果
     * @throws Exception
     */
    public static <T> T post(RestTemplate restTemplate, String path, Object request, Class<T> responseType) throws Exception {
        return post(restTemplate, path, request, responseType, null);
    }


    /**
     * HTTP GET
     *
     * @param restTemplate restTemplate
     * @param path         请求路径
     * @param request      请求体，object，如果是个map，会自动转化为url参数
     * @param responseType 响应类型
     * @param <T>          响应类型
     * @param extraHeaders 额外的请求头
     * @return 结果
     * @throws Exception
     */
    public static <T> T get(RestTemplate restTemplate,
                            String path, Object request,
                            Class<T> responseType,
                            Map<String, String> extraHeaders) throws Exception {

        log.debug("begin to do http get. path:{}, request params:{}", path, request);

        //add accept json
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);


        //添加额外的请求头
        if (MapUtils.isNotEmpty(extraHeaders)) {
            for (Map.Entry<String, String> entry : extraHeaders.entrySet()) {
                headers.set(entry.getKey(), entry.getValue());
            }
        }

        HttpEntity<?> entity = new HttpEntity<>(headers);

        //url
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(path);

        //添加参数
        Map<String, ?> params = (Map<String, ?>) request;
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, ?> urlParam : params.entrySet()) {
                builder.queryParam(urlParam.getKey(), urlParam.getValue());
            }
        }
        final URI getURI = builder.build().encode().toUri();

        HttpEntity<T> response = restTemplate.exchange(getURI, HttpMethod.GET, entity, responseType);
        
		if (log.isDebugEnabled()) {
			log.debug("end to do http get. path:{}, request params:{}. response body: {}", path, request,response.getBody());
		}

        return response.getBody();
    }


    /**
     * HTTP GET
     *
     * @param restTemplate restTemplate
     * @param path         请求路径
     * @param request      请求体，object，如果是个map，会自动转化为url参数
     * @param responseType 响应类型
     * @param <T>          响应类型
     * @return 结果
     * @throws Exception
     */
    public static <T> T get(RestTemplate restTemplate, String path, Object request, Class<T> responseType) throws Exception {
        return get(restTemplate, path, request, responseType, null);
    }


    private final static MultiValueMap<String, String> convert(Map<String, String> extraHeaders) {

        MultiValueMap<String, String> headers = new LinkedMultiValueMap();

        if (MapUtils.isNotEmpty(extraHeaders)) {
            for (Map.Entry<String, String> entry : extraHeaders.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }

        return headers;
    }


}