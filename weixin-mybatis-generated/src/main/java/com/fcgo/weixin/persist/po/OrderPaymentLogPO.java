package com.fcgo.weixin.persist.po;

import java.math.BigDecimal;
import java.util.Date;

public class OrderPaymentLogPO {
    /**
     * 主键
     * column: ord_payment_log.id
     */
    private Integer id;

    /**
     * 订单ID
     * column: ord_payment_log.order_id
     */
    private Integer orderId;

    /**
     * 商品ID
     * column: ord_payment_log.product_id
     */
    private Integer productId;

    /**
     * 支付状态0待支付1支付完成2支付失败
     * column: ord_payment_log.payment_status
     */
    private Integer paymentStatus;

    /**
     * 退款状态0退款成功1退款失败
     * column: ord_payment_log.refund_status
     */
    private Integer refundStatus;

    /**
     * 金额
     * column: ord_payment_log.transaction_amount
     */
    private BigDecimal transactionAmount;

    /**
     * 交易号
     * column: ord_payment_log.bank_transaction_number
     */
    private String bankTransactionNumber;

    /**
     * 支付方式
     * column: ord_payment_log.payment_type
     */
    private String paymentType;

    /**
     * 失败原因
     * column: ord_payment_log.cause_of_failure
     */
    private String causeOfFailure;

    /**
     * 创建时间
     * column: ord_payment_log.create_time
     */
    private Date createTime;

    /**
     * 更新时间
     * column: ord_payment_log.update_time
     */
    private Date updateTime;

    /**
     * 添加人
     * column: ord_payment_log.create_name
     */
    private String createName;

    /**
     * 更新人
     * column: ord_payment_log.update_name
     */
    private String updateName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getBankTransactionNumber() {
        return bankTransactionNumber;
    }

    public void setBankTransactionNumber(String bankTransactionNumber) {
        this.bankTransactionNumber = bankTransactionNumber == null ? null : bankTransactionNumber.trim();
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }

    public String getCauseOfFailure() {
        return causeOfFailure;
    }

    public void setCauseOfFailure(String causeOfFailure) {
        this.causeOfFailure = causeOfFailure == null ? null : causeOfFailure.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }
}