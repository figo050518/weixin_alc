package com.fcgo.weixin.persist.po;

import java.math.BigDecimal;
import java.util.Date;

public class OrderItemPO {
    /**
     * 主键自增
     * column: ord_order_item.ID
     */
    private Integer id;

    /**
     * 删除状态:0=代表未删除,1=代表已删除
     * column: ord_order_item.IS_DELETE
     */
    private Integer isDelete;

    /**
     * 添加时间
     * column: ord_order_item.CREATE_TIME
     */
    private Date createTime;

    /**
     * 修改时间
     * column: ord_order_item.UPDATE_TIME
     */
    private Date updateTime;

    /**
     * 订单表（ord_order）主键
     * column: ord_order_item.ORDER_ID
     */
    private Integer orderId;

    /**
     * 商品表（product)主键
     * column: ord_order_item.PRODUCT_ID
     */
    private Integer productId;

    /**
     * 图片url
     * column: ord_order_item.PRODUCT_PIC_URL
     */
    private String productPicUrl;

    /**
     * 商品名称
     * column: ord_order_item.PRODUCT_NAME
     */
    private String productName;

    /**
     * 商品数量
     * column: ord_order_item.PRODUCT_QUANT
     */
    private Integer productQuant;

    /**
     * 商品当时的规格名称
     * column: ord_order_item.PRODUCT_SPEC
     */
    private String productSpec;

    /**
     * 商品单价
     * column: ord_order_item.PRODUCT_PRICE
     */
    private BigDecimal productPrice;

    /**
     * 非常购商品单价
     * column: ord_order_item.fcg_product_price
     */
    private BigDecimal fcgProductPrice;

    /**
     * 商品总金额
     * column: ord_order_item.PRODUCT_TOTAL_AMOUNT
     */
    private BigDecimal productTotalAmount;

    /**
     * 退货状态：0=未退货,1=代表退货
     * column: ord_order_item.RETURN_STATE
     */
    private Integer returnState;

    /**
     * 添加人员
     * column: ord_order_item.create_name
     */
    private String createName;

    /**
     * 修改人员
     * column: ord_order_item.update_name
     */
    private String updateName;

    /**
     * 删除人员
     * column: ord_order_item.delete_name
     */
    private String deleteName;

    /**
     * 删除时间
     * column: ord_order_item.delete_time
     */
    private Date deleteTime;

    /**
     * 运费
     * column: ord_order_item.freight
     */
    private BigDecimal freight;

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

    public Integer getProductQuant() {
        return productQuant;
    }

    public void setProductQuant(Integer productQuant) {
        this.productQuant = productQuant;
    }

    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec == null ? null : productSpec.trim();
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getFcgProductPrice() {
        return fcgProductPrice;
    }

    public void setFcgProductPrice(BigDecimal fcgProductPrice) {
        this.fcgProductPrice = fcgProductPrice;
    }

    public BigDecimal getProductTotalAmount() {
        return productTotalAmount;
    }

    public void setProductTotalAmount(BigDecimal productTotalAmount) {
        this.productTotalAmount = productTotalAmount;
    }

    public Integer getReturnState() {
        return returnState;
    }

    public void setReturnState(Integer returnState) {
        this.returnState = returnState;
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

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }
}