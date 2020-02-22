package com.fcgo.weixin.common.util;

import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HttpRequestUtils {
    public static Map<String, Object> getRequestParams(HttpServletRequest request){

        Map<String, Object> simpleMap = new HashMap<>();
        Map<String,String[]> paramsMap;
        if(Objects.nonNull(paramsMap = request.getParameterMap())) {
            paramsMap.entrySet().forEach(entry->{
                String[] values = entry.getValue();
                String value = ArrayUtils.isEmpty(values) ? null : values[0];
                simpleMap.put(entry.getKey(), value);
            });
        }

        return simpleMap;
    }
}
