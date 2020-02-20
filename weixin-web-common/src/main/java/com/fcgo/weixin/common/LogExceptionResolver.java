package com.fcgo.weixin.common;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.fcgo.weixin.common.log.LogCategory;

public class LogExceptionResolver extends SimpleMappingExceptionResolver {

    protected Log logger = LogFactory.getLog(LogCategory.EXCEPTION.toString());

    private Map<String, String> mediaTypes;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        return super.doResolveException(request, response, handler, ex);
    }

    @Override
    protected void logException(Exception ex, HttpServletRequest request) {
        try {
            StringBuffer sb = new StringBuffer();
            sb.append(buildLogMessage(ex, request));
            sb.append(",request URL=[" + request.getRequestURL() + "?" + request.getQueryString() + "] \r\n");
            sb.append("Cookie Info=[");
            Cookie[] cookies = request.getCookies();
            if (ArrayUtils.isNotEmpty(cookies)) {
                for (int j = 0; j < cookies.length; j++) {
                    if (j != 0) {
                        sb.append(",");
                    }
                    sb.append(cookies[j].getName() + "=" + cookies[j].getValue());
                }
            }
            sb.append("]" + "\r\t");
            logger.error(sb.toString(), ex);
        }
        catch (Exception e) {
            logger.error("信息：LogExceptionResolver logException !", e);
        }
    }

    public Map<String, String> getMediaTypes() {
        return mediaTypes;
    }

    public void setMediaTypes(Map<String, String> mediaTypes) {
        this.mediaTypes = mediaTypes;
    }
}
