package com.fcgo.weixin.persist.po;

import java.util.Date;

public class ProductGroupPO {
    /**
     * 主键
     * column: pdt_product_group.ID
     */
    private Integer id;

    /**
     * 是否删除 0代表未删除 1代表删除
     * column: pdt_product_group.IS_DELETE
     */
    private Integer isDelete;

    /**
     * 创建时间
     * column: pdt_product_group.CREATE_TIME
     */
    private Date createTime;

    /**
     * 更新时间
     * column: pdt_product_group.UPDATE_TIME
     */
    private Date updateTime;

    /**
     * 分组名称
     * column: pdt_product_group.GROUP_NAME
     */
    private String groupName;

    /**
     * 店铺ID
     * column: pdt_product_group.SHOP_ID
     */
    private Integer shopId;

    /**
     * 添加人
     * column: pdt_product_group.create_name
     */
    private String createName;

    /**
     * 更新人
     * column: pdt_product_group.update_name
     */
    private String updateName;

    /**
     * 卖家ID
     * column: pdt_product_group.seller_id
     */
    private Integer sellerId;
    
    /**
     * 冗余字段 商品数量
     */
    private Integer productCount;

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
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

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }
    
    
}