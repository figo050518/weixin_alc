package com.fcgo.weixin.intercept;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrossOriginInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o) throws Exception {
        //两种解决方案
        String method = req.getMethod();
        String url = req.getRequestURI();
        String origin = req.getHeader("origin");
        logger.info("CrossOriginInterceptor preHandle getHeader(origin), url {} origin {} method {}",url, origin, method);
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Methods","*");
        resp.setHeader("Access-Control-Allow-Headers","Content-Type,Access-Control-Allow-Headers,Content-Length,Accept,Authorization,X-Requested-With,token");
        //post precheck
        if(method.equalsIgnoreCase("OPTIONS")){
            logger.info("in CrossOriginInterceptor.preHandle do {} url {}",method, url);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object o, Exception e) throws Exception {

    }
}
