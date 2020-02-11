package com.fcgo.weixin.persist.po;

import java.util.Date;

public class SellerShopPO {
    /**
     * 主键
     * column: seller_shop.ID
     */
    private Integer id;

    /**
     * 逻辑删除标示，0=未删除，1=已删除
     * column: seller_shop.IS_DELETE
     */
    private Integer isDelete;

    /**
     * 创建时间
     * column: seller_shop.CREATE_TIME
     */
    private Date createTime;

    /**
     * 修改时间
     * column: seller_shop.UPDATE_TIME
     */
    private Date updateTime;

    /**
     * 店铺名称
     * column: seller_shop.SHOP_NAME
     */
    private String shopName;

    /**
     * 店铺描述
     * column: seller_shop.SHOP_DESC
     */
    private String shopDesc;

    /**
     * 卖家ID
     * column: seller_shop.seller_id
     */
    private Integer sellerId;

    /**
     * 门店logo地址id（image_info表的主键）
     * column: seller_shop.LOGO_URL_ID
     */
    private String logoUrlId;

    /**
     * 店铺背景地址id（image_info表的主键）
     * column: seller_shop.BG_URL_ID
     */
    private String bgUrlId;

    /**
     * 添加人
     * column: seller_shop.create_name
     */
    private String createName;

    /**
     * 更新人
     * column: seller_shop.update_name
     */
    private String updateName;

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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc == null ? null : shopDesc.trim();
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getLogoUrlId() {
        return logoUrlId;
    }

    public void setLogoUrlId(String logoUrlId) {
        this.logoUrlId = logoUrlId == null ? null : logoUrlId.trim();
    }

    public String getBgUrlId() {
        return bgUrlId;
    }

    public void setBgUrlId(String bgUrlId) {
        this.bgUrlId = bgUrlId == null ? null : bgUrlId.trim();
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