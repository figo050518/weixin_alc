package com.fcgo.weixin.application.dto;

import java.math.BigDecimal;

/**
 * 确认订单信息显示的商品详细
 * 
 * @author xiahan
 */
public class OrderConfirmProductDTO {

    // 产品Id
    private int productId;

    // 产品规格属性值
    private String productSpecName;

    private String productName;

    // 产品数量
    private int productCount;

    private BigDecimal productPrice;

    private String picUrl;

    private int specId;

    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public OrderConfirmProductDTO() {
        super();
    }

    public OrderConfirmProductDTO(int productId, int specId, int productCount) {
        this.productId = productId;
        this.specId = specId;
        this.productCount = productCount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductSpecName() {
        return productSpecName;
    }

    public void setProductSpecName(String productSpecName) {
        this.productSpecName = productSpecName;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

}
