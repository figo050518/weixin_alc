package com.fcgo.weixin.application.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

public class GetWeiXinOpenId {
    private static Logger log = Logger.getLogger(GetWeiXinOpenId.class);

    public static JSONObject httpsRequestToJsonObject(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            StringBuffer buffer = httpsRequest(requestUrl, requestMethod, outputStr);
            jsonObject = JSONObject.fromObject(buffer.toString());
        }
        catch (ConnectException ce) {
            log.error("连接超时：" + ce.getMessage());
        }
        catch (Exception e) {
            log.error("https请求异常：" + e.getMessage());
        }
        return jsonObject;
    }

    private static StringBuffer httpsRequest(String requestUrl, String requestMethod, String output)
            throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException, MalformedURLException,
            IOException, ProtocolException, UnsupportedEncodingException {
        URL url = new URL(requestUrl);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod(requestMethod);
        if (null != output) {
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(output.getBytes("UTF-8"));
            outputStream.close();
        }
        // 从输入流读取返回内容
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        StringBuffer buffer = new StringBuffer();
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        inputStream = null;
        connection.disconnect();
        return buffer;
    }

    public static Map<String, String> getOpenId(String code) throws UnsupportedEncodingException {
        Map<String, String> result = new HashMap<String, String>();
        String openId = null;
        String oauth_url =
                WeiXinConstants.oauth_url.replace("APPID", WeiXinConstants.appid)
                        .replace("SECRET", WeiXinConstants.appsecret).replace("CODE", code);
        log.info("oauth_url:" + oauth_url);
        JSONObject jsonObject = GetWeiXinOpenId.httpsRequestToJsonObject(oauth_url, "POST", null);
        log.info("jsonObject:" + jsonObject);
        Object errorCode = jsonObject.get("errcode");
        if (errorCode != null) {
            log.info("code不合法");
        }
        else {
            openId = jsonObject.getString("openid");
            result.put("openid", openId);
            String refresh_token_url =
                    WeiXinConstants.refresh_token_url.replace("APPID", WeiXinConstants.appid).replace("REFRESH_TOKEN",
                            jsonObject.getString("refresh_token"));

            JSONObject jsonObjectrefresh = GetWeiXinOpenId.httpsRequestToJsonObject(refresh_token_url, "POST", null);
            if (jsonObjectrefresh.get("errcode") != null) {
                log.info("access code不合法");
            }
            else {
                String access_token_url =
                        WeiXinConstants.access_token_url.replace("OPENID", openId).replace("ACCESS_TOKEN",
                                jsonObjectrefresh.get("access_token").toString());
                JSONObject jsonObjectAccess = GetWeiXinOpenId.httpsRequestToJsonObject(access_token_url, "POST", null);
                Object errorCodeNick = jsonObjectAccess.get("errcode");
                if (errorCodeNick != null) {
                    log.info("access code不合法");
                }
                else {
                    String nickName = jsonObjectAccess.getString("nickname");
                    result.put("nickName", nickName);
                }
            }
        }
        return result;
    }
}
