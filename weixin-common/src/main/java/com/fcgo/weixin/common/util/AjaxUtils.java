package com.fcgo.weixin.common.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.WebRequest;

public class AjaxUtils {

    private AjaxUtils() {
    }

    private static final String HTTP_AJAX_UPLOADER_HEADER_NAME = "ajaxUpload";
    private static final String HTTP_AJAX_HEADER_NAME = "X-Requested-With";
    private static final String HTTP_AJAX_HEADER_VALUE = "XMLHttpRequest";

    public static boolean isAjaxRequest(WebRequest webRequest) {
        String requestedWith = webRequest.getHeader(HTTP_AJAX_HEADER_NAME);
        return requestedWith != null && HTTP_AJAX_HEADER_VALUE.equals(requestedWith);
    }

    public static boolean isAjaxRequest(HttpServletRequest httpServletRequest) {
        String requestedWith = httpServletRequest.getHeader(HTTP_AJAX_HEADER_NAME);
        return requestedWith != null && HTTP_AJAX_HEADER_VALUE.equals(requestedWith);
    }

    public static boolean isAjaxUploadRequest(WebRequest webRequest) {
        return webRequest.getParameter(HTTP_AJAX_UPLOADER_HEADER_NAME) != null;
    }

    public static boolean isAjaxUploadRequest(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getParameter(HTTP_AJAX_UPLOADER_HEADER_NAME) != null;
    }

}
