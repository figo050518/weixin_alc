package com.fcgo.weixin.application.impl.weixin;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.SSLContext;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import com.fcgo.weixin.application.dto.PayRequest;
import com.fcgo.weixin.application.weixin.JsApiUtil;
import com.fcgo.weixin.application.weixin.MessageBuilder;
import com.fcgo.weixin.application.weixin.NetWorkCenter;
import com.fcgo.weixin.application.weixin.WeiXinConstants;
import com.fcgo.weixin.application.weixin.WeixinPayService;
import com.fcgo.weixin.common.util.XmlUtil;

@Service
public class WeixinPayServiceImpl implements WeixinPayService {

    @Override
    public Map<String, String> jsApiPay(PayRequest payRequest, String openid) {
        final Map<String, String> rm = new HashMap<String, String>();
        rm.put("appid", WeiXinConstants.appid);// appid
        rm.put("device_info", "WEB");// 设备号
        rm.put("trade_type", "JSAPI");// 接口方式
        rm.put("mch_id", WeiXinConstants.mch_id);// 商户id
        rm.put("notify_url", payRequest.getNotify_url());// 回调通知url
        rm.put("openid", openid);
        rm.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", ""));
        rm.put("body", payRequest.getBody());
        // rm.put("detail", payRequest.getDetail());
        rm.put("out_trade_no", payRequest.getOut_trade_no());// 订单编号
        rm.put("total_fee", String.valueOf((int) (payRequest.getTotal_fee() * 100)));// 支付金额
        rm.put("spbill_create_ip", payRequest.getSpbill_create_ip());// 申请支付ip
        // rm.put("product_id", payRequest.getProduct_id());
        /******* 生成签名 *********/
        String sign = JsApiUtil.paySign(rm, WeiXinConstants.key);
        rm.put("requestResult", "fail");
        /****** 构建请求包的xml内容 *********/
        MessageBuilder mb = new MessageBuilder();
        mb.addData("appid", WeiXinConstants.appid);
        mb.addData("trade_type", "JSAPI");
        mb.addData("mch_id", WeiXinConstants.mch_id);
        mb.addData("device_info", "WEB");
        mb.addData("notify_url", rm.get("notify_url"));
        mb.addData("openid", openid);
        mb.addData("nonce_str", rm.get("nonce_str"));
        mb.addData("body", payRequest.getBody());
        // mb.addData("detail", payRequest.getDetail());
        mb.addData("out_trade_no", payRequest.getOut_trade_no());
        mb.addData("total_fee", rm.get("total_fee"));
        mb.addData("spbill_create_ip", payRequest.getSpbill_create_ip());
        // mb.addData("product_id", payRequest.getProduct_id());
        mb.addData("sign", sign);
        mb.surroundWith("xml");
        /* 发起预支付请求 */
        NetWorkCenter.post("https://api.mch.weixin.qq.com/pay/unifiedorder", mb.toString(),
                new NetWorkCenter.ResponseCallback() {

                    public void onResponse(int resultCode, String resultContent) {
                        if (resultCode == HttpStatus.SC_OK) {
                            SAXReader reader = new SAXReader();
                            try {
                                Document document = reader.read(new StringReader(resultContent));
                                Map<String, Object> resultMap = XmlUtil.Dom2Map(document);// 将返回的xml解析成map
                                // 判断是通信标识，非交易标识，交易是否成功需要查看 result_code 来判断
                                if (resultMap.get("return_code").toString().equals("FAIL")) {
                                    rm.put("err_msg", resultMap.get("return_msg").toString());
                                    return;
                                }
                                else {
                                    // 判断业务结果
                                    if (resultMap.get("result_code").toString().equals("FAIL")) {
                                        rm.put("err_msg", resultMap.get("err_code_des").toString());
                                        return;
                                    }
                                    else {
                                        System.out.println("xiahan----prepay_id"
                                                + resultMap.get("prepay_id").toString());
                                        rm.put("prepay_id", resultMap.get("prepay_id").toString());
                                        rm.put("requestResult", "success");
                                    }
                                }
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
        Map<String, String> jsPayParams = new HashMap<String, String>();
        jsPayParams.put("appId", rm.get("appid"));
        jsPayParams.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
        jsPayParams.put("nonceStr", rm.get("nonce_str"));
        jsPayParams.put("package", "prepay_id=" + rm.get("prepay_id"));
        jsPayParams.put("signType", "MD5");
        String paySign = JsApiUtil.paySign(jsPayParams, WeiXinConstants.key);
        rm.put("appId", jsPayParams.get("appId"));
        rm.put("timeStamp", jsPayParams.get("timeStamp"));
        rm.put("nonceStr", jsPayParams.get("nonceStr"));
        rm.put("package", "prepay_id=" + rm.get("prepay_id"));
        rm.put("jsignType", jsPayParams.get("signType"));
        rm.put("paySign", paySign);
        return rm;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean refund(PayRequest payRequest) {
        final Map<String, String> rm = new HashMap<String, String>();
        rm.put("appid", WeiXinConstants.appid);
        rm.put("mch_id", WeiXinConstants.mch_id);
        rm.put("device_info", "WEB");
        rm.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", ""));
        rm.put("transaction_id", payRequest.getTransactionId());
        rm.put("out_refund_no", payRequest.getRefundNumber());
        rm.put("total_fee", String.valueOf((int) (payRequest.getTotal_fee() * 100)));// 支付金额
        rm.put("refund_fee", payRequest.getRefundMoney().multiply(new BigDecimal(100)).setScale(0).toString());
        rm.put("op_user_id", WeiXinConstants.mch_id);
        /******* 生成签名 *********/
        String sign = JsApiUtil.paySign(rm, WeiXinConstants.key);
        /****** 构建请求包的xml内容 *********/
        MessageBuilder mb = new MessageBuilder();
        mb.addData("appid", WeiXinConstants.appid);
        mb.addData("mch_id", WeiXinConstants.mch_id);
        mb.addData("device_info", "WEB");
        mb.addData("nonce_str", rm.get("nonce_str"));
        mb.addData("sign", sign);
        mb.addData("transaction_id", payRequest.getTransactionId());
        mb.addData("out_refund_no", payRequest.getRefundNumber());
        mb.addData("total_fee", String.valueOf((int) (payRequest.getTotal_fee() * 100)));// 支付金额
        mb.addData("refund_fee", payRequest.getRefundMoney().multiply(new BigDecimal(100)).setScale(0).toString());
        mb.addData("op_user_id", WeiXinConstants.mch_id);
        mb.surroundWith("xml");
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream instream = new FileInputStream(new File("12344"));
            try {
                keyStore.load(instream, "1373268002".toCharArray());
            }
            finally {
                instream.close();
            }
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, "1373268002".toCharArray()).build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf =
                    new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null,
                            SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            HttpPost httppost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");
            try {
                StringEntity se = new StringEntity(mb.toString());
                httppost.setEntity(se);
                CloseableHttpResponse responseEntry = httpclient.execute(httppost);
                try {
                    HttpEntity entity = responseEntry.getEntity();
                    if (entity != null) {
                        SAXReader saxReader = new SAXReader();
                        Document document = saxReader.read(entity.getContent());
                        Element rootElt = document.getRootElement();
                        String returnCode = rootElt.elementText("return_code");
                        String returnMsg = rootElt.elementText("return_msg");
                        System.out.println(returnMsg);
                        JSONObject result = new JSONObject();
                        if (returnCode.equals("SUCCESS")) {

                        }
                        else {
                            result.put("status", "false");
                            result.put("msg", rootElt.elementText("err_code_des"));
                        }

                    }
                    EntityUtils.consume(entity);
                }
                catch (Exception e) {
                    System.out.println(e);
                }
                finally {
                    responseEntry.close();
                }
            }
            finally {
                httpclient.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("status", "error");
            result.put("msg", e.getMessage());
        }
        return true;
    }

    @Override
    public boolean hasTranactionLog(String transactionId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasRefundTranactionLog(String transactionId) {
        // TODO Auto-generated method stub
        return false;
    }

}
