package com.fcgo.weixin.application.weixin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.fcgo.weixin.application.weixin.aes.MD5Util;
import com.fcgo.weixin.application.weixin.aes.SHA1;

/**
 * @author 陈江林
 */
public class JsApiUtil {
    /**
     * 计算 wx.config() 中需要使用的签名。每个页面都需要进行计算
     * 
     * @param jsApiTicket 微信 js-sdk提供的ticket
     * @param nonceStr 随机字符串
     * @param timestame 时间戳
     * @param url 当前网页的URL，不包含#及其后面部分
     * @return 合法的签名
     * @throws Exception 获取签名异常
     */
    public static String sign(String jsApiTicket, String nonceStr, long timestame, String url) throws Exception {
        Map<String, String> paramMap = new TreeMap<String, String>();
        paramMap.put("jsapi_ticket", jsApiTicket);
        paramMap.put("noncestr", nonceStr);
        paramMap.put("timestamp", Long.toString(timestame));
        paramMap.put("url", url);

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            sb.append("&").append(entry.toString());
        }
        return SHA1.getSHA1HexString(sb.substring(1));
    }

    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     * 
     * @param params
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String paySign(Map<String, String> params, String key) {
        StringBuffer sb = new StringBuffer();
        List<String> list = new ArrayList<String>();
        Set es = params.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                list.add(k);
            }
        }
        Collections.sort(list);
        for (String k : list) {
            sb.append(k + "=" + params.get(k) + "&");
        }
        sb.append("key=" + key.trim());
        return MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
    }

    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     * 
     * @param params
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String md5Sign(Map<String, String> params) {
        StringBuffer sb = new StringBuffer();
        List<String> list = new ArrayList<String>();
        Set es = params.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v)) {
                list.add(k);
            }
        }
        Collections.sort(list);
        for (String k : list) {
            sb.append("&" + k + "=" + params.get(k));
        }
        return MD5Util.MD5Encode(sb.toString().replaceFirst("&", ""), "UTF-8").toUpperCase();
    }
}
