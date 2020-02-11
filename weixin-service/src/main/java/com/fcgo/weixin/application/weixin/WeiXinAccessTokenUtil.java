package com.fcgo.weixin.application.weixin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.fcgo.weixin.application.dto.WeiXinAccessTokenDTO;

public class WeiXinAccessTokenUtil {

    private static DefaultHttpClient httpClient = new DefaultHttpClient();

    /**
     * 发送Get请求
     * 
     * @param url
     * @param params
     * @return
     */
    public static String getAccessToken() {
        String body = null;
        try {
            // Get请求
            HttpGet httpget =
                    new HttpGet(
                            "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx9c9db1e990c78c20&secret=b60fd8261bed9a531946d7a5dc3bd989");
            // 设置参数
            httpget.setURI(new URI(
                    "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx9c9db1e990c78c20&secret=b60fd8261bed9a531946d7a5dc3bd989"));
            // 发送请求
            HttpResponse httpresponse = httpClient.execute(httpget);
            // 获取返回数据
            HttpEntity entity = httpresponse.getEntity();
            body = EntityUtils.toString(entity);
            if (entity != null) {
                entity.consumeContent();
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        WeiXinAccessTokenDTO weiXinAccessTokenDTO =
                (WeiXinAccessTokenDTO) JSONObject.toBean(JSONObject.fromObject(body), WeiXinAccessTokenDTO.class);
        return weiXinAccessTokenDTO.getAccess_token();
    }

}
