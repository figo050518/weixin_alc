package com.fcgo.weixin.persist.po;

import java.util.Date;

public class ShoppingCartItemPO {
    /**
     * 主键
     * column: shopping_cart_item.ID
     */
    private Integer id;

    /**
     * 删除标识：0=未删除，1=已删除
     * column: shopping_cart_item.IS_DELETE
     */
    private Integer isDelete;

    /**
     * 创建时间
     * column: shopping_cart_item.CREATE_TIME
     */
    private Date createTime;

    /**
     * 更新时间
     * column: shopping_cart_item.UPDATE_TIME
     */
    private Date updateTime;

    /**
     * 购物车表主键
     * column: shopping_cart_item.SHOP_CART_ID
     */
    private Integer shopCartId;

    /**
     * 商品ID
     * column: shopping_cart_item.PRODUCT_ID
     */
    private Integer productId;

    /**
     * 添加人
     * column: shopping_cart_item.create_name
     */
    private String createName;

    /**
     * 更新人
     * column: shopping_cart_item.update_name
     */
    private String updateName;

    /**
     * 商品名称
     * column: shopping_cart_item.product_name
     */
    private String productName;

    /**
     * 规格名称
     * column: shopping_cart_item.spec_name
     */
    private String specName;

    /**
     * 商品数量
     * column: shopping_cart_item.product_count
     */
    private Integer productCount;

    /**
     * 商品主图URL
     * column: shopping_cart_item.product_url
     */
    private String productUrl;

    /**
     * 店铺ID
     * column: shopping_cart_item.shop_id
     */
    private Integer shopId;

    /**
     * 商家店铺名称
     * column: shopping_cart_item.shop_name
     */
    private String shopName;

    /**
     * 规格ID
     * column: shopping_cart_item.spec_id
     */
    private Integer specId;

    /**
     * 卖家ID
     * column: shopping_cart_item.seller_id
     */
    private Integer sellerId;

    /**
     * 非常购的规格ID
     * column: shopping_cart_item.fcg_spec_id
     */
    private Integer fcgSpecId;

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

    public Integer getShopCartId() {
        return shopCartId;
    }

    public void setShopCartId(Integer shopCartId) {
        this.shopCartId = shopCartId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName == null ? null : specName.trim();
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl == null ? null : productUrl.trim();
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getFcgSpecId() {
        return fcgSpecId;
    }

    public void setFcgSpecId(Integer fcgSpecId) {
        this.fcgSpecId = fcgSpecId;
    }
}