package com.fcgo.weixin.application.dto;

import java.math.BigDecimal;

/**
 * 发起预支付请求实体
 * 
 * @author 陈江林
 */
public class PayRequest {
    private String notify_url;// 接收微信支付结果通知的url地址
    private String spbill_create_ip;// 终端 IP(必须)
    private String product_id;// 商品id
    private String body;// 商品描述(必须)
    private String detail;// 商品详情(非必须)
    private String out_trade_no;// 订单号(必须)
    private double total_fee;// 订单总额,单位为元(必须)

    private String transactionId;
    private String refundNumber;
    private BigDecimal refundMoney;

    private BigDecimal refundParentCommison;

    public BigDecimal getRefundParentCommison() {
        return refundParentCommison;
    }

    public void setRefundParentCommison(BigDecimal refundParentCommison) {
        this.refundParentCommison = refundParentCommison;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getRefundNumber() {
        return refundNumber;
    }

    public void setRefundNumber(String refundNumber) {
        this.refundNumber = refundNumber;
    }

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
        /*
         * try { this.notify_url = URLEncoder.encode(notify_url, "UTF-8"); } catch (UnsupportedEncodingException e) {
         * e.printStackTrace(); }
         */
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public double getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(double total_fee) {
        this.total_fee = total_fee;
    }

}
