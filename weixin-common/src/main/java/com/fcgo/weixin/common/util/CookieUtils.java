package com.fcgo.weixin.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

/**
 * Cookie 辅助类
 * 
 * @author xiahan
 */
public class CookieUtils {

    /**
     * userEmail cookie名称
     */
    public static final String USER_TEPEPHONE_COOKIE_NAME = "USER_TEPEPHONE";

    /**
     * userName cookie名称
     */
    public static final String USER_NAME_COOKIE_NAME = "USER_NAME";

    /**
     * 用户身份买家
     */
    public static final String IS_BUYER = "IS_BUYER";

    /**
     * 用户身份卖家
     */
    public static final String IS_SELLER = "IS_SELLER";

    /**
     * SESSION ID cookie名称
     */
    public static final String COOKIE_JSESSIONID = "JSESSIONID";

    public static final String COOKIE_FCG_SIGN_IN_TOKEN = "COOKIE_FCG_SIGN_IN_TOKEN";

    /**
     * aizhu 登录时间
     */
    public static final String COOKIE_FCG_SIGN_IN_TIME = "FCG_SIGN_IN_TIME";

    /**
     * 登录后最后一次访问时间
     */
    public static final String LAST_TIME_ACCESS_TIME = "LAST_TIME_ACCESS_TIME";

    /**
     * 获得cookie
     * 
     * @param request HttpServletRequest
     * @param name cookie name
     * @return if exist return cookie, else return null.
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Assert.notNull(request);
        Cookie[] cookies = request.getCookies();
        if ((cookies != null) && (cookies.length > 0)) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    return c;
                }
            }
        }
        return null;
    }

    /**
     * 获得cookie对应name的Value值
     * 
     * @param request HttpServletRequest
     * @param name cookie name
     * @return if exist return cookieValue, else return null.
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Assert.notNull(request);
        Cookie cookie = getCookie(request, name);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    /**
     * 根据部署路径，将cookie保存在根目录。
     * 
     * @param request
     * @param response
     * @param name
     * @param value
     * @param expiry
     * @param domain
     * @return
     */
    public static Cookie addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
            Integer expiry, String domain) {
        Cookie cookie = new Cookie(name, value);
        if (expiry != null) {
            cookie.setMaxAge(expiry);
        }
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        String ctx = request.getContextPath();
        cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
        response.addCookie(cookie);
        return cookie;
    }

    /**
     * 取消cookie
     * 
     * @param request
     * @param response
     * @param name
     * @param domain
     */
    public static void cancleCookie(HttpServletRequest request, HttpServletResponse response, String name, String domain) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        String ctx = request.getContextPath();
        cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        response.addCookie(cookie);
    }
}
