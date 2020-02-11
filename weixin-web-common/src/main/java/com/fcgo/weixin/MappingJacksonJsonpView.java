package com.fcgo.weixin;

import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
 * 13-7-31
 * 
 * @author heshunfeng
 * @since QB_LV_2003_11
 */
public class MappingJacksonJsonpView extends MappingJacksonJsonView {
    public static final String DEFAULT_CONTENT_TYPE = "application/javascript";

    @Override
    public String getContentType() {
        return DEFAULT_CONTENT_TYPE;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("GET".equals(request.getMethod().toUpperCase())) {
            @SuppressWarnings("unchecked")
            Map<String, String[]> params = request.getParameterMap();
            if (params.containsKey("callback")) {
                Writer writer = response.getWriter();
                writer.append(params.get("callback")[0]);
                writer.append("(");
                super.render(model, request, response);
                writer.append(");");
                response.setContentType(DEFAULT_CONTENT_TYPE);
            }
            else {
                super.render(model, request, response);
            }
        }
        else {
            super.render(model, request, response);
        }
    }
}
