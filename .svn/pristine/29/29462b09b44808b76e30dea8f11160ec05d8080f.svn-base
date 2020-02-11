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

import org.apache.commons.lang.StringUtils;

import com.fcgo.weixin.application.user.UserInfoService;
import com.fcgo.weixin.application.weixin.WeiXinConstants;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.util.ApplicationContextHolder;
import com.fcgo.weixin.common.util.HttpSessionProvider;
import com.fcgo.weixin.persist.po.UserInfoPO;

/**
 * 买家授权登录
 * 
 * @author xiahanxzh
 */
public class OpenIdAuthFilter implements Filter {

    private UserInfoService userInfoService;
    private List<String> excludeNeedLoginURIList = new ArrayList<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userInfoService = ApplicationContextHolder.getApplicationContext().getBean(UserInfoService.class);
        excludeNeedLoginURIList.add("/uc");
        excludeNeedLoginURIList.add("/user/login");
        excludeNeedLoginURIList.add("/user/register");
        excludeNeedLoginURIList.add("/user");
        excludeNeedLoginURIList.add("/resources");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        FcgHttpServletResponseWrapper httpServletResponse =
                new FcgHttpServletResponseWrapper((HttpServletResponse) response);
        String userRequestURI = httpServletRequest.getRequestURI();
        String queryString = httpServletRequest.getQueryString();
        userRequestURI = (StringUtils.isEmpty(queryString) ? userRequestURI : userRequestURI + "?" + queryString);
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(httpServletRequest, "session_attr_user");
        if (needAuth(userRequestURI)) {
            if (baseSessionUserDTO != null && baseSessionUserDTO.getUserId() > 0) {
                UserInfoPO userInfoPO = userInfoService.findById(baseSessionUserDTO.getUserId());
                if (userInfoPO != null && StringUtils.isNotBlank(userInfoPO.getWxId())) {
                    return;
                }
            }
            else {
                String codeUrl =
                        WeiXinConstants.user_code_url
                                .replace("APPID", WeiXinConstants.appid)
                                .replace("SECRET", WeiXinConstants.appsecret)
                                .replace(
                                        "REDIRECT_URI",
                                        URLEncoder.encode("http://m.izhu100.com/weixin/auth?backUrl=" + userRequestURI,
                                                "UTF-8"));
                httpServletResponse.sendRedirect(codeUrl);
                return;
            }
        }
        chain.doFilter(httpServletRequest, httpServletResponse);
    }

    private boolean needAuth(String userRequestURI) {
        boolean needAuthed = true;
        for (String excludeNeedLoginURI : excludeNeedLoginURIList) {
            if (userRequestURI.startsWith(excludeNeedLoginURI)) {
                needAuthed = false;
                return needAuthed;
            }
        }
        return needAuthed;
    }

    @Override
    public void destroy() {

    }

}
