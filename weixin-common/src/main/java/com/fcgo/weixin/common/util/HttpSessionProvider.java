package com.fcgo.weixin.common.util;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * HttpSession提供类
 * 
 * @author xiahan
 */
public class HttpSessionProvider {

    public static Serializable getAttribute(HttpServletRequest request, String name) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Serializable) session.getAttribute(name);
        }
        else {
            return null;
        }
    }

    public static void setAttribute(HttpServletRequest request, HttpServletResponse response, String name,
            Serializable value) {
        HttpSession session = request.getSession();
        session.setAttribute(name, value);
    }

    public static String getSessionId(HttpServletRequest request, HttpServletResponse response) {
        return request.getSession().getId();
    }

    public static void logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
