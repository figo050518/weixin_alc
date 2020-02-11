package com.fcgo.weixin.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.log.LogCategory;
import com.fcgo.weixin.common.util.AjaxUtils;
import com.fcgo.weixin.common.util.HttpSessionProvider;
import com.fcgo.weixin.dto.SignInUtils;

/**
 * @author xiahan
 */
public class FrontSessionFilter implements Filter {

    protected Log logger = LogFactory.getLog(LogCategory.SYSTEM.toString());

    private List<String> needLoginURIList = new ArrayList<String>();

    private List<String> excludeNeedLoginURIList = new ArrayList<String>();

    private HttpSessionProvider sessionProvider;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // sessionProvider = ApplicationContextHolder.getApplicationContext().getBean(HttpSessionProvider.class);
        needLoginURIList.add("/uc");
        excludeNeedLoginURIList.add("/user");
        excludeNeedLoginURIList.add("/user/login");
        excludeNeedLoginURIList.add("/user/register");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        FcgHttpServletResponseWrapper httpServletResponse =
                new FcgHttpServletResponseWrapper((HttpServletResponse) response);
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) sessionProvider.getAttribute(httpServletRequest, "session_attr_user");
        boolean isSignIn = SignInUtils.isLogon(baseSessionUserDTO);
        // 获取请求URI
        String userRequestURI = httpServletRequest.getRequestURI();
        if (!isSignIn && needLogin(userRequestURI)) {
            // Ajax请求处理
            if (AjaxUtils.isAjaxRequest(httpServletRequest)) {
                httpServletResponse.setStatus(HttpStatus.SC_UNAUTHORIZED);
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setHeader("Content-Type", "text/html;charset=UTF-8");
                httpServletResponse.getWriter().print("登录超时,请重新登录");
            }
            // 非Ajax请求处理
            else {
                // 获取请求参数
                String queryString = httpServletRequest.getQueryString();
                String requestMethod = httpServletRequest.getMethod();
                // 如果是post请求，在没有登录时
                if (requestMethod.equalsIgnoreCase("post") || requestMethod.equalsIgnoreCase("put")
                        || requestMethod.equalsIgnoreCase("delete")) {
                    String refUrl = null;
                    if (null != httpServletRequest.getHeader("Referer")) {
                        refUrl = httpServletRequest.getHeader("Referer");
                    }
                    // referer不为空返回之前地址
                    if (StringUtils.isNotEmpty(refUrl)) {
                        httpServletResponse.sendRedirect(refUrl);
                        return;
                    }
                    // referer为空返回首页
                    else {
                        httpServletResponse.sendRedirect("/user/login");
                        logger.info("POST方式请求:'" + userRequestURI + "',没有登录而且没有Referer,登录后跳转至首页");
                        return;
                    }
                }
                userRequestURI =
                        (StringUtils.isEmpty(queryString) ? userRequestURI : userRequestURI + "?" + queryString);
                httpServletResponse.sendRedirect("/user/login?backUrl=" + ""
                        + URLEncoder.encode(userRequestURI, "utf-8"));
            }
            return;
        }
        chain.doFilter(httpServletRequest, httpServletResponse);
    }

    private boolean needLogin(String userRequestURI) {
        boolean needLogined = false;
        for (String excludeNeedLoginURI : excludeNeedLoginURIList) {
            if (userRequestURI.startsWith(excludeNeedLoginURI)) {
                needLogined = false;
                return needLogined;
            }
        }
        for (String needLoginURI : needLoginURIList) {
            if (userRequestURI.startsWith(needLoginURI)) {
                needLogined = true;
                break;
            }
        }
        return needLogined;
    }

    @Override
    public void destroy() {
    }

}
