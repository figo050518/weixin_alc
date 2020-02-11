package com.fcgo.weixin.common.util.logisticsTrack;

import java.util.HashMap;

public class Query {
    public static void main(String[] args) throws Exception {

        String param = "{\"com\":\"***\",\"num\":\"***\"}";
        String customer = "***";
        String key = "****";
        String sign = MD5.encode(param + key + customer);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("param", param);
        params.put("sign", sign);
        params.put("customer", customer);
        String resp;
        try {
            resp = new HttpRequest().postData("http://poll.kuaidi100.com/poll/query.do", params, "utf-8").toString();
            System.out.println(resp);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
