package com.fcgo.weixin.persist.model;

public class BrandAccountFlow {
    private Integer id;

    private Integer brandAccountId;

    private Short flowType;

    private Integer orderId;

    private Long orderCode;

    private Double amout;

    private Integer createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBrandAccountId() {
        return brandAccountId;
    }

    public void setBrandAccountId(Integer brandAccountId) {
        this.brandAccountId = brandAccountId;
    }

    public Short getFlowType() {
        return flowType;
    }

    public void setFlowType(Short flowType) {
        this.flowType = flowType;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Long getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Long orderCode) {
        this.orderCode = orderCode;
    }

    public Double getAmout() {
        return amout;
    }

    public void setAmout(Double amout) {
        this.amout = amout;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }
}