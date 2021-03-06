package com.fcgo.weixin.persist.po;

import java.math.BigDecimal;
import java.util.Date;

public class ProductPO {
    /**
     * 主键
     * column: pdt_product.ID
     */
    private Integer id;

    /**
     * 逻辑删除标示
     * column: pdt_product.IS_DELETE
     */
    private Integer isDelete;

    /**
     * 创建时间
     * column: pdt_product.CREATE_TIME
     */
    private Date createTime;

    /**
     * 修改时间
     * column: pdt_product.UPDATE_TIME
     */
    private Date updateTime;

    /**
     * 商品名称
     * column: pdt_product.PRO_NAME
     */
    private String proName;

    /**
     * 上下架标示，0=下架，1=上架
     * column: pdt_product.UP_STATE
     */
    private Integer upState;

    /**
     * 上/下架时间
     * column: pdt_product.UP_TIME
     */
    private Date upTime;

    /**
     * 分组ID
     * column: pdt_product.GROUP_ID
     */
    private Integer groupId;

    /**
     * 店铺ID
     * column: pdt_product.SHOP_ID
     */
    private Integer shopId;

    /**
     * 来源，1=自营，2=平台上架
     * column: pdt_product.FROM_TYPE
     */
    private Integer fromType;

    /**
     * 添加人
     * column: pdt_product.create_name
     */
    private String createName;

    /**
     * 更新人
     * column: pdt_product.update_name
     */
    private String updateName;

    /**
     * 卖家ID
     * column: pdt_product.seller_id
     */
    private Integer sellerId;

    /**
     * 非常购商品的id
     * column: pdt_product.fcg_product_id
     */
    private Integer fcgProductId;

    /**
     * 非常购商品的类型1：一般贸易2：跨境保税 3：海外直邮
     * column: pdt_product.fcg_product_type
     */
    private Integer fcgProductType;

    /**
     * 最高价
     * 供排序搜索用
     * column: pdt_product.max_price
     */
    private BigDecimal maxPrice;

    /**
     * 最低价
     * 供排序搜索用
     * column: pdt_product.min_price
     */
    private BigDecimal minPrice;

    /**
     * 售卖数量
     * 供排序搜索用
     * column: pdt_product.sales_count
     */
    private Integer salesCount;

    /**
     * 运费
     * column: pdt_product.freight
     */
    private BigDecimal freight;

    /**
     * 非常购目录ID
     * column: pdt_product.fcg_category_id
     */
    private Integer fcgCategoryId;

    /**
     * 目录的值
     * column: pdt_product.category_name
     */
    private String categoryName;

    /**
     * 如果是非常购商品，表示涨幅多少
     * column: pdt_product.amount_increase
     */
    private String amountIncrease;

    /**
     * 商品详细描述
     * column: pdt_product.product_DESC
     */
    private String productDesc;

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

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
    }

    public Integer getUpState() {
        return upState;
    }

    public void setUpState(Integer upState) {
        this.upState = upState;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getFromType() {
        return fromType;
    }

    public void setFromType(Integer fromType) {
        this.fromType = fromType;
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

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getFcgProductId() {
        return fcgProductId;
    }

    public void setFcgProductId(Integer fcgProductId) {
        this.fcgProductId = fcgProductId;
    }

    public Integer getFcgProductType() {
        return fcgProductType;
    }

    public void setFcgProductType(Integer fcgProductType) {
        this.fcgProductType = fcgProductType;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public Integer getFcgCategoryId() {
        return fcgCategoryId;
    }

    public void setFcgCategoryId(Integer fcgCategoryId) {
        this.fcgCategoryId = fcgCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getAmountIncrease() {
        return amountIncrease;
    }

    public void setAmountIncrease(String amountIncrease) {
        this.amountIncrease = amountIncrease == null ? null : amountIncrease.trim();
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc == null ? null : productDesc.trim();
    }
}