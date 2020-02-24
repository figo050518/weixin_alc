package com.fcgo.weixin.intercept;


import com.fcgo.weixin.annotation.IgnoreSession;
import com.fcgo.weixin.common.constants.HeadKey;
import com.fcgo.weixin.common.exception.SessionExpireException;
import com.fcgo.weixin.common.util.HttpRequestUtils;
import com.fcgo.weixin.service.AccountService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SecurityInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);


    //限制本地IP访问
    @Setter
    private List<String> local = new LinkedList<>();

    // 这些url不会进行校验。 例如 "/notify"
    @Getter
    @Setter
    private List<String> excludeUrls;

    //是否启用
    @Getter
    private boolean debugEnable = false;

    public void setIsDebugEnable(boolean debugEnable){
        this.debugEnable = debugEnable;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o) throws Exception {
        String method = httpServletRequest.getMethod();
        if(method.equalsIgnoreCase("OPTIONS")){
            logger.info("in CrossOriginInterceptor.preHandle do {} url {}",method, httpServletRequest.getRequestURL());
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        Map<String, Object> params = HttpRequestUtils.getRequestParams(httpServletRequest);

        //(1)不需要校验SESSION的场景.
        // (1)exclude和debug模式,(2)私有网络模式
        if (this.isIgnore(httpServletRequest, params, o)) {
            return true;
        }
        Map<String, String> paramsOfHead = HttpRequestUtils.getRequestParamsOfHead(httpServletRequest);

        //(2)校验session
        this.validateSession(httpServletRequest, params, paramsOfHead);

        return true;
    }

    private boolean urlContains(String requestUri, List<String> excludeUrls) {
        for (String excludeUri : excludeUrls) {
            if (requestUri.equals(excludeUri) || requestUri.startsWith(excludeUri) || requestUri.startsWith("/ufo-gateway" + excludeUri) || requestUri.endsWith(excludeUri)) {
                return true;
            }
        }
        return false;
    }

    private boolean isIgnore(HttpServletRequest request, Map<String, Object> params, Object o) {

        //如果请求url包含在过滤的url，则直接返回. 请求url可能是 "/gateway/xxx”这种包含了context的。
        logger.debug("enter isIgnore");
        if (excludeUrls != null) {
            final String requestUri = request.getRequestURI();
            if (this.urlContains(requestUri, excludeUrls)) {
                logger.info("isIgnore check url in excludeUrls,uri {}",requestUri);
                return true;
            }
        }

        //如果请求是本地请求（来自私有网络）
        if (this.isLocalRequestMatch(request)) {
            logger.info("isIgnore check ip is local");
            return true;
        }

        //配置文件配置为 is_debug_enable 为true，并且请求携带参数debug为XYZ，就放行
        if (debugEnable && "XYZ".equals(request.getParameter("debug"))) {
            logger.debug("isIgnore check debug model");
            return true;
        }
        logger.debug("end to isIgnore check");

        //含有IgnoreSession注解的接口放行
        if(o.getClass().isAssignableFrom(HandlerMethod.class)){
            HandlerMethod handlerMethod = (HandlerMethod)o;
            Method bridgedMethod =handlerMethod.getMethod();
            if(bridgedMethod.isAnnotationPresent(IgnoreSession.class)){
                return true;
            }
        }

        return false;
    }

    private String getIP(HttpServletRequest httpServletRequest) {
        String ip = httpServletRequest.getHeader("X-Real-IP");
        if (StringUtils.isEmpty(ip)) {
            ip = httpServletRequest.getRemoteAddr();
        }
        return ip;
    }
    /**
     * 本地IP限制
     * @param request
     * @return
     */
    private boolean isLocalRequestMatch(HttpServletRequest request) {
        if (CollectionUtils.isEmpty(this.local)) {
            return false;
        }
        final String requestUri = request.getRequestURI();
        final String ip = this.getIP(request);

        //ip is blank or has multi ip
        if(StringUtils.isEmpty(ip) || ip.contains(",")){
            return false;
        }

        try {
            InetAddress  inetAddress = InetAddress.getByName(ip);
            if (this.urlContains(requestUri, local) && (inetAddress.isSiteLocalAddress() || inetAddress.isLoopbackAddress())) {
                return true;
            }
        } catch (UnknownHostException e) {
            logger.error("unknown ip", e);
        }

        return false;
    }


    private void validateSession(HttpServletRequest httpServletRequest,
                                 Map<String, Object> params,Map<String, String> paramsOfHead) throws SessionExpireException {
        // params为空,说明接口无参数, 无需校验
        String url = httpServletRequest.getRequestURI();

        //5 解析客户端传入的COOKIE中的session值
        Cookie[] cookies = httpServletRequest.getCookies();
        String jSessionID = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                //解析sessionid
                if ("JSESSIONID".equals(cookie.getName())) {
                    jSessionID = cookie.getValue();
                    break;
                }
            }
        }

        //如果cookie中没有jSessionID , 但接口又必须校验会话, 则返回 HTTP 401, 需要重新登录.
        if (jSessionID == null) {
            jSessionID = paramsOfHead.get(HeadKey.token);
            logger.warn("check session failed, can not find session id in cookies, check session info failed, url {}, get from local {}",
                    url, jSessionID);
        }

        if (jSessionID == null){
            logger.warn("check session fail from request head, url {}", url);
            throw new SessionExpireException(); //重新登录
        }

        if (AccountService.isLoginBySession(jSessionID)){
            //重新登录
            logger.warn("match session in local cache, url {} jSessionID {}", url, jSessionID);
            return;
        }
        logger.warn("not match session in local cache, url {} jSessionID {}", url, jSessionID);
        HttpSession currentSession = httpServletRequest.getSession();
        if (Objects.nonNull(currentSession) && currentSession.getId().equals(jSessionID)){
            return;
        }
        throw new SessionExpireException();
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
