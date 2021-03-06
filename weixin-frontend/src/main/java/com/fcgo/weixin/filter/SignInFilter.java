package com.fcgo.weixin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fcgo.weixin.application.user.UserLoginService;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.dto.SignInFcgToken;
import com.fcgo.weixin.common.util.ApplicationContextHolder;
import com.fcgo.weixin.common.util.DateUtil;
import com.fcgo.weixin.common.util.HttpSessionProvider;
import com.fcgo.weixin.dto.SignInResult;
import com.fcgo.weixin.dto.SignInUtils;

/**
 * 登录
 * 
 * @author xiahan
 */
public class SignInFilter implements Filter {

    private UserLoginService userLoginService;

    /**
     * 检查该登录用户是否需要重新登录
     * 
     * @param httpServletRequest
     * @param httpServletResponse
     */
    private void checkSignInPeriod(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(httpServletRequest, "session_attr_user");
        boolean isSignIn = SignInUtils.isLogon(baseSessionUserDTO);
        if (!isSignIn) {
            return;
        }
        SignInFcgToken signInQBToken = SignInUtils.getSignInQBToken(httpServletRequest);
        String lastTimeAccessTime = SignInUtils.getAizhuLastTimeAccessTime(httpServletRequest);
        boolean isPeriod =
                userLoginService.checkSignInPeriod(signInQBToken.getSignInTelephone(), signInQBToken.getToken(),
                        lastTimeAccessTime);
        if (isPeriod) {
            // 注销用户
            SignInUtils.aizhuSignOut(httpServletRequest, httpServletResponse);
            // 注销会话
            HttpSessionProvider.logout(httpServletRequest, httpServletResponse);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletRequest.getRequestURI();
        // 检查是否需要重新登录FCG
        checkSignInPeriod(httpServletRequest, httpServletResponse);
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(httpServletRequest, "session_attr_user");
        boolean isSignIn = SignInUtils.isLogon(baseSessionUserDTO);
        if (isSignIn) {
            // 登录后记录此次访问时间
            SignInUtils.addAizhuLastTimeAccessTime(httpServletRequest, httpServletResponse, new Long(DateUtil
                    .getBJCurrentDate().getTime()).toString());
        }
        if (!isSignIn) {
            SignInResult signInResult = initSignInFcg(httpServletRequest, httpServletResponse);
            if (!signInResult.isSignInSucc()) {
                // 清理历史记录(用户非正常退出系统)
                SignInUtils.aizhuSignOut(httpServletRequest, httpServletResponse);
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * 登录成功后初始化用户信息
     * 
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    private SignInResult initSignInFcg(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        SignInFcgToken signInFcgToken = SignInUtils.getSignInQBToken(httpServletRequest);
        if (!signInFcgToken.isAllowSignIn()) {
            return new SignInResult(false);
        }
        // 登录该用户
        try {
            // 登录token合法性校验
            String lastTimeAccessTime = SignInUtils.getAizhuLastTimeAccessTime(httpServletRequest);
            boolean signInSuccTokenValid =
                    userLoginService.validateSignInSuccToken(signInFcgToken.getSignInTelephone(),
                            signInFcgToken.getToken(), lastTimeAccessTime);
            if (!signInSuccTokenValid) {
                return new SignInResult(false);
            }
            userLoginService.initSignInFcg(httpServletRequest, httpServletResponse, signInFcgToken);
            return new SignInResult(true);
        }
        catch (Exception e) {
            System.out.println(e);
            return new SignInResult(false);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userLoginService = ApplicationContextHolder.getApplicationContext().getBean(UserLoginService.class);

    }

}
