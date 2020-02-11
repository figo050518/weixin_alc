package com.fcgo.weixin.dto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.dto.SignInFcgToken;
import com.fcgo.weixin.common.util.CookieUtils;
import com.fcgo.weixin.common.util.DateUtil;

/**
 * 登录帮助类
 * 
 * @author xiahan
 */
public class SignInUtils {

    /**
     * 是否登录
     * 
     * @param baseDTO
     * @return
     */
    public static boolean isLogon(BaseSessionUserDTO baseSessionUserDTO) {
        return baseSessionUserDTO != null && baseSessionUserDTO.getNickName() != null;
    }

    /**
     * 获取SignInAizhuToken
     * 
     * @param httpServletRequest
     * @return
     */
    public static SignInFcgToken getSignInQBToken(HttpServletRequest httpServletRequest) {
        String token = CookieUtils.getCookieValue(httpServletRequest, CookieUtils.COOKIE_FCG_SIGN_IN_TOKEN);
        String signInTelephone = CookieUtils.getCookieValue(httpServletRequest, CookieUtils.USER_TEPEPHONE_COOKIE_NAME);
        String signInQBTime = SignInUtils.getQBSignInTime(httpServletRequest);
        return new SignInFcgToken(token, signInTelephone, signInQBTime);
    }

    /**
     * 获取QB登录时间
     * 
     * @param request
     * @param response
     * @return
     */
    public static String getQBSignInTime(HttpServletRequest request) {
        return CookieUtils.getCookieValue(request, CookieUtils.COOKIE_FCG_SIGN_IN_TIME);
    }

    /**
     * 获取最后访问时间
     * 
     * @param request
     * @return
     */
    public static String getAizhuLastTimeAccessTime(HttpServletRequest request) {
        return CookieUtils.getCookieValue(request, CookieUtils.LAST_TIME_ACCESS_TIME);
    }

    /**
     * aizhu注销
     * 
     * @param request
     * @param response
     */
    public static void aizhuSignOut(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.addCookie(request, response, CookieUtils.COOKIE_FCG_SIGN_IN_TOKEN, StringUtils.EMPTY, -1,
                ".fcg.com");
        CookieUtils.addCookie(request, response, CookieUtils.LAST_TIME_ACCESS_TIME, StringUtils.EMPTY, -1, ".fcg.com");
        CookieUtils
                .addCookie(request, response, CookieUtils.COOKIE_FCG_SIGN_IN_TIME, StringUtils.EMPTY, -1, ".fcg.com");
    }

    /**
     * 添加最后访问时间
     * 
     * @param request
     * @param response
     * @param signInQBTime
     */
    public static void addAizhuLastTimeAccessTime(HttpServletRequest request, HttpServletResponse response,
            String lastTimeAccessTime) {
        CookieUtils.addCookie(request, response, CookieUtils.LAST_TIME_ACCESS_TIME, lastTimeAccessTime, -1, ".fcg.com");
    }

    /**
     * 添加aizhu登录时间
     * 
     * @param request
     * @param response
     */
    public static void addQBSignInTime(HttpServletRequest request, HttpServletResponse response, String signInTime) {
        CookieUtils.addCookie(request, response, CookieUtils.COOKIE_FCG_SIGN_IN_TIME, signInTime, -1, ".fcg.com");
    }

    /**
     * aizhu的登录
     * 
     * @param request
     * @param response
     */
    public static void initCookieInfo(HttpServletRequest request, HttpServletResponse response,
            SignInFcgToken signInAizhuToken) {
        // clearHistoryRecord(request, response);
        CookieUtils.addCookie(request, response, CookieUtils.COOKIE_FCG_SIGN_IN_TOKEN, signInAizhuToken.getToken(), -1,
                ".fcg.com");
        // 登录成功后自动保存邮箱一个月
        CookieUtils.addCookie(request, response, CookieUtils.USER_TEPEPHONE_COOKIE_NAME,
                signInAizhuToken.getSignInTelephone(), 30 * 24 * 60 * 60, ".fcg.com");
        addAizhuLastTimeAccessTime(request, response, new Long(DateUtil.getBJCurrentDate().getTime()).toString());
    }

    private static void clearHistoryRecord(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.addCookie(request, response, CookieUtils.USER_TEPEPHONE_COOKIE_NAME, StringUtils.EMPTY, 0, "");
        CookieUtils.addCookie(request, response, CookieUtils.COOKIE_FCG_SIGN_IN_TOKEN, StringUtils.EMPTY, 0, "");
    }

}
