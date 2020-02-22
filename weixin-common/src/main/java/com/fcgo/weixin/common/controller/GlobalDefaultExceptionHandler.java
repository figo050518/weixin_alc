package com.fcgo.weixin.common.controller;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.fcgo.weixin.common.exception.ServiceException;
import com.fcgo.weixin.common.exception.SessionExpireException;
import com.fcgo.weixin.common.util.HttpRequestUtils;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private static String YH_ERR_CODE_HEADER = "X-Alc-Code";
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request,
                                            HttpServletResponse response, Exception e) throws Exception {


        final String url = request.getRequestURI();
        final Map<String, Object> params = HttpRequestUtils.getRequestParams(request);
        response.addHeader(YH_ERR_CODE_HEADER, "500");

        //用户未登录,或登录 会话超时
        if (e instanceof SessionExpireException) {
            log.info("session expire at url:{}, params:{}", url, params);
            response.setStatus(401);
            return new ModelAndView();
        }

        //如果是请求URL匹配不了，则返回400
        if (e instanceof UnsatisfiedServletRequestParameterException
                || e instanceof MissingServletRequestParameterException
                || e instanceof MethodArgumentTypeMismatchException) {
            log.warn("can not find validate request mapping at {}, params is {}", request.getRequestURI(), params);
            response.setStatus(HttpStatus.SC_BAD_REQUEST);
            return new ModelAndView();
        }

        //如果是业务异常，则返回http 200，并且构造json消息体中错误码&错误内容
        if (e instanceof ServiceException
                || e instanceof Exception) {
            int code;
            String desc;
             if (e instanceof ServiceException) {  //服务异常，不能直接返回给客户端，必须映射一下
                ServiceException serviceException = (ServiceException) e;
                Pair<Integer, String> codeMsgPair = getMessageCode(serviceException);
                code = codeMsgPair.getLeft();
                desc = codeMsgPair.getRight();

                log.info("service exception happened at:{}, code:{}, desc:{},  params is: {}",
                        url, code, desc, params);
            } else {
                code = 500;
                desc = "服务暂时异常,请稍等";
                log.warn("exception happened at:{}, code:{}, desc:{}, uri:{},  params is: {}",
                        url, code, desc, request.getRequestURI(), params, e);
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            }
            ModelAndView mv = getErrorJsonView(code, desc);
            return mv;
        }
        log.warn("gateway other exception happened at uri:{}, request: {}, params is: {}, e  {}",
                 url, params, e);

        //其他异常，返回500
        response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        return new ModelAndView();
    }

    public static ModelAndView getErrorJsonView(int code, String message) {
        ModelAndView modelAndView = new ModelAndView();
        FastJsonJsonView jsonView = new FastJsonJsonView();
        Map<String, Object> errorInfoMap = new HashMap<>();
        errorInfoMap.put("code", code);
        errorInfoMap.put("message", message);
        jsonView.setAttributesMap(errorInfoMap);
        modelAndView.setView(jsonView);
        return modelAndView;
    }

    private Pair<Integer, String> getMessageCode(ServiceException serviceException){

        return Pair.of(serviceException.getCode(), serviceException.getErrorMessage());
    }
}
