package com.fcgo.weixin.persist.model;

public class OrderDeliveryTrace {
    private Integer id;

    private String orderCode;

    private String deliveryNum;

    private Integer status;

    private String cancelReason;

    private Integer cancelFrom;

    private Integer createTime;

    private Integer dadaUpdateTime;

    private Integer dmId;

    private String dmName;

    private String dmMobile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public String getDeliveryNum() {
        return deliveryNum;
    }

    public void setDeliveryNum(String deliveryNum) {
        this.deliveryNum = deliveryNum == null ? null : deliveryNum.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason == null ? null : cancelReason.trim();
    }

    public Integer getCancelFrom() {
        return cancelFrom;
    }

    public void setCancelFrom(Integer cancelFrom) {
        this.cancelFrom = cancelFrom;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getDadaUpdateTime() {
        return dadaUpdateTime;
    }

    public void setDadaUpdateTime(Integer dadaUpdateTime) {
        this.dadaUpdateTime = dadaUpdateTime;
    }

    public Integer getDmId() {
        return dmId;
    }

    public void setDmId(Integer dmId) {
        this.dmId = dmId;
    }

    public String getDmName() {
        return dmName;
    }

    public void setDmName(String dmName) {
        this.dmName = dmName == null ? null : dmName.trim();
    }

    public String getDmMobile() {
        return dmMobile;
    }

    public void setDmMobile(String dmMobile) {
        this.dmMobile = dmMobile == null ? null : dmMobile.trim();
    }
}