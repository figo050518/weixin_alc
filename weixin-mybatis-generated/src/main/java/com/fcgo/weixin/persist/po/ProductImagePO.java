package com.fcgo.weixin.persist.po;

import java.util.Date;

public class ProductImagePO {
    /**
     * 主键
     * column: pdt_product_image.ID
     */
    private Integer id;

    /**
     * 是否删除
     * column: pdt_product_image.IS_DELETE
     */
    private Integer isDelete;

    /**
     * 创建时间
     * column: pdt_product_image.CREATE_TIME
     */
    private Date createTime;

    /**
     * 更新时间
     * column: pdt_product_image.UPDATE_TIME
     */
    private Date updateTime;

    /**
     * 图片地址
     * column: pdt_product_image.IMG_URL
     */
    private String imgUrl;

    /**
     * 添加人
     * column: pdt_product_image.create_name
     */
    private String createName;

    /**
     * 更新人
     * column: pdt_product_image.update_name
     */
    private String updateName;

    /**
     * 排序顺序
     * column: pdt_product_image.display_order
     */
    private Integer displayOrder;

    /**
     * 商品ID
     * column: pdt_product_image.product_id
     */
    private Integer productId;

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
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

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}