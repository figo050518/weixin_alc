package com.fcgo.weixin.application.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 确认订单信息显示的商品详细
 * 
 * @author xiahan
 */
public class OrderConfirmDetailDTO {

    // 产品Id
    private String shopName;

    private int shopId;

    private int sellerId;

    private List<OrderConfirmProductDTO> orderConfirmProductDTOs;

    // 订单的总运费
    private BigDecimal singleTotalFreight;

    private BigDecimal singleTotalProductPrice;

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getSingleTotalFreight() {
        return singleTotalFreight;
    }

    public void setSingleTotalFreight(BigDecimal singleTotalFreight) {
        this.singleTotalFreight = singleTotalFreight;
    }

    public BigDecimal getSingleTotalProductPrice() {
        return singleTotalProductPrice;
    }

    public void setSingleTotalProductPrice(BigDecimal singleTotalProductPrice) {
        this.singleTotalProductPrice = singleTotalProductPrice;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<OrderConfirmProductDTO> getOrderConfirmProductDTOs() {
        return orderConfirmProductDTOs;
    }

    public void setOrderConfirmProductDTOs(List<OrderConfirmProductDTO> orderConfirmProductDTOs) {
        this.orderConfirmProductDTOs = orderConfirmProductDTOs;
    }

}
