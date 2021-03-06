package com.fcgo.weixin.persist.po;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderRefundRequestPO {
    /**
     * 自增主键 column: ord_refund_request.ID
     */
    private Integer id;

    /**
     * 删除状态0代表未删除1代表已删除 column: ord_refund_request.IS_DELETE
     */
    private Integer isDelete;

    /**
     * 添加时间 column: ord_refund_request.CREATE_TIME
     */
    private Date createTime;

    /**
     * 修改时间 column: ord_refund_request.UPDATE_TIME
     */
    private Date updateTime;

    /**
     * 售后编号 column: ord_refund_request.REFUND_NUMBER
     */
    private String refundNumber;

    /**
     * 买家ID column: ord_refund_request.BUYER_ID
     */
    private Integer buyerId;

    private List<String> imageUrl;

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 卖家ID column: ord_refund_request.SELLER_ID
     */
    private Integer sellerId;

    /**
     * 订单表（ord_order）主键 column: ord_refund_request.ORDER_ID
     */
    private Integer orderId;

    /**
     * 订单编号 column: ord_refund_request.ORDER_NUM
     */
    private String orderNum;

    /**
     * 商品表（product）主键 column: ord_refund_request.PRODUCT_ID
     */
    private Integer productId;

    /**
     * 产品图片url column: ord_refund_request.PRODUCT_PIC_URL
     */
    private String productPicUrl;

    /**
     * 商品名称 column: ord_refund_request.PRODUCT_NAME
     */
    private String productName;

    /**
     * 商品单价 column: ord_refund_request.PRODUCT_PRICE
     */
    private BigDecimal productPrice;

    /**
     * 商品属性 column: ord_refund_request.PRODUCT_SPEC
     */
    private String productSpec;

    /**
     * 退货商品数量 column: ord_refund_request.PRODUCT_QUANT
     */
    private Integer productQuant;

    /**
     * 商品总额 column: ord_refund_request.PRODUCT_TOTAL_AMOUNT
     */
    private BigDecimal productTotalAmount;

    /**
     * 是否退货1退货0不退 column: ord_refund_request.IS_TUIHUO
     */
    private Integer isTuihuo;

    /**
     * 退款金额 column: ord_refund_request.REFUND_MONEY
     */
    private BigDecimal refundMoney;

    /**
     * 售后原因 column: ord_refund_request.REFUND_REASON
     */
    private String refundReason;

    /**
     * 退货拒绝原因 column: ord_refund_request.REFUSED_REASON
     */
    private String refusedReason;

    /**
     * 售后状态 1 申请中2已通过 3已完成 4：拒绝 column: ord_refund_request.REFUND_STATUS
     */
    private String refundStatus;

    /**
     * 审核人员 column: ord_refund_request.AUDIT_NAME
     */
    private String auditName;

    /**
     * 审核时间 column: ord_refund_request.AUDIT_TIME
     */
    private Date auditTime;

    /**
     * 完成时间 column: ord_refund_request.FINISH_TIME
     */
    private Date finishTime;

    /**
     * 添加人员 column: ord_refund_request.CREATE_NAME
     */
    private String createName;

    /**
     * 修改人员 column: ord_refund_request.UPDATE_NAME
     */
    private String updateName;

    /**
     * 删除人员 column: ord_refund_request.DELETE_NAME
     */
    private String deleteName;

    /**
     * 删除时间 column: ord_refund_request.DELETE_TIME
     */
    private Date deleteTime;

    /**
     * 审核不通过的原因 column: ord_refund_request.REDUNDS_REASON
     */
    private String redundsReason;

    /**
     * 需要退还的佣金 column: ord_refund_request.REFUND_COMMISON
     */
    private BigDecimal refundCommison;

    /**
     * 非常购商品当时售卖的价格 column: ord_refund_request.fcg_product_price
     */
    private BigDecimal fcgProductPrice;

    /**
     * 是否已经交给非常购审核的标识,1:已经，0:没有 column: ord_refund_request.FCG_REFUND_FLAG
     */
    private Integer fcgRefundFlag;

    /**
     * 商品数量 column: ord_refund_request.product_count
     */
    private Integer productCount;

    /**
     * 退货时填写的物流公司 column: ord_refund_request.logistics_company
     */
    private String logisticsCompany;

    /**
     * 退货时填写的物流单号 column: ord_refund_request.logistics_number
     */
    private String logisticsNumber;

    /**
     * 买家取消的标识 column: ord_refund_request.cancel_flag
     */
    private Integer cancelFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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

    public String getRefundNumber() {
        return refundNumber;
    }

    public void setRefundNumber(String refundNumber) {
        this.refundNumber = refundNumber == null ? null : refundNumber.trim();
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductPicUrl() {
        return productPicUrl;
    }

    public void setProductPicUrl(String productPicUrl) {
        this.productPicUrl = productPicUrl == null ? null : productPicUrl.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec == null ? null : productSpec.trim();
    }

    public Integer getProductQuant() {
        return productQuant;
    }

    public void setProductQuant(Integer productQuant) {
        this.productQuant = productQuant;
    }

    public BigDecimal getProductTotalAmount() {
        return productTotalAmount;
    }

    public void setProductTotalAmount(BigDecimal productTotalAmount) {
        this.productTotalAmount = productTotalAmount;
    }

    public Integer getIsTuihuo() {
        return isTuihuo;
    }

    public void setIsTuihuo(Integer isTuihuo) {
        this.isTuihuo = isTuihuo;
    }

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason == null ? null : refundReason.trim();
    }

    public String getRefusedReason() {
        return refusedReason;
    }

    public void setRefusedReason(String refusedReason) {
        this.refusedReason = refusedReason == null ? null : refusedReason.trim();
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus == null ? null : refundStatus.trim();
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName == null ? null : auditName.trim();
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
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

    public String getDeleteName() {
        return deleteName;
    }

    public void setDeleteName(String deleteName) {
        this.deleteName = deleteName == null ? null : deleteName.trim();
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getRedundsReason() {
        return redundsReason;
    }

    public void setRedundsReason(String redundsReason) {
        this.redundsReason = redundsReason == null ? null : redundsReason.trim();
    }

    public BigDecimal getRefundCommison() {
        return refundCommison;
    }

    public void setRefundCommison(BigDecimal refundCommison) {
        this.refundCommison = refundCommison;
    }

    public BigDecimal getFcgProductPrice() {
        return fcgProductPrice;
    }

    public void setFcgProductPrice(BigDecimal fcgProductPrice) {
        this.fcgProductPrice = fcgProductPrice;
    }

    public Integer getFcgRefundFlag() {
        return fcgRefundFlag;
    }

    public void setFcgRefundFlag(Integer fcgRefundFlag) {
        this.fcgRefundFlag = fcgRefundFlag;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany == null ? null : logisticsCompany.trim();
    }

    public String getLogisticsNumber() {
        return logisticsNumber;
    }

    public void setLogisticsNumber(String logisticsNumber) {
        this.logisticsNumber = logisticsNumber == null ? null : logisticsNumber.trim();
    }

    public Integer getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(Integer cancelFlag) {
        this.cancelFlag = cancelFlag;
    }
}
