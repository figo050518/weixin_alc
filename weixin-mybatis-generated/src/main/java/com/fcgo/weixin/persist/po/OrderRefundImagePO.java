package com.fcgo.weixin.persist.po;

import java.util.Date;

public class OrderRefundImagePO {
    /**
     * 主键
     * column: ord_refund_image.ID
     */
    private Integer id;

    /**
     * 是否删除
     * column: ord_refund_image.IS_DELETE
     */
    private Integer isDelete;

    /**
     * 创建时间
     * column: ord_refund_image.CREATE_TIME
     */
    private Date createTime;

    /**
     * 更新时间
     * column: ord_refund_image.UPDATE_TIME
     */
    private Date updateTime;

    /**
     * 图片地址
     * column: ord_refund_image.IMG_URL
     */
    private String imgUrl;

    /**
     * 添加人
     * column: ord_refund_image.create_name
     */
    private String createName;

    /**
     * 更新人
     * column: ord_refund_image.update_name
     */
    private String updateName;

    /**
     * 排序顺序
     * column: ord_refund_image.display_order
     */
    private Integer displayOrder;

    /**
     * 退款申请的ID
     * column: ord_refund_image.ord_refund_id
     */
    private Integer ordRefundId;

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

    public Integer getOrdRefundId() {
        return ordRefundId;
    }

    public void setOrdRefundId(Integer ordRefundId) {
        this.ordRefundId = ordRefundId;
    }
}