package com.fcgo.weixin.common.util;

import com.fcgo.weixin.common.constants.HeadKey;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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

    public static Map<String, String> getRequestParamsOfHead(HttpServletRequest request){

        Map<String, String> simpleMap = new HashMap<>();
        List<String> headParams = Lists.newArrayList(HeadKey.token);

        for (String headParam : headParams){
            String headVal = request.getHeader(headParam);
            simpleMap.put(headParam, headVal);
        }

        return simpleMap;
    }
}
