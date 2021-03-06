package com.fcgo.weixin.persist.po;

import java.math.BigDecimal;
import java.util.Date;

public class FinanceBillPO {
    /**
     * 主键 column: finance_bill.ID
     */
    private Integer id;

    /**
     * 0代表未删除，1代表删除 column: finance_bill.IS_DELETE
     */
    private Integer isDelete;

    /**
     * 创建时间 column: finance_bill.CREATE_TIME
     */
    private Date createTime;

    /**
     * 更新时间 column: finance_bill.UPDATE_TIME
     */
    private Date updateTime;

    /**
     * 当前余额 column: finance_bill.CURRENT_BALANCE
     */
    private BigDecimal currentBalance;

    /**
     * 产生的金额 column: finance_bill.GEN_AMOUNT
     */
    private BigDecimal genAmount;

    /**
     * 资金流水方向：1=代表支出，2=代表收入 column: finance_bill.GEN_EVENT
     */
    private Integer genEvent;

    /**
     * 店铺ID column: finance_bill.SHOP_ID
     */
    private Integer shopId;

    /**
     * 卖家ID column: finance_bill.SELLER_ID
     */
    private Integer sellerId;

    /**
     * 创建人 column: finance_bill.CREATE_NAME
     */
    private String createName;

    /**
     * 更新人 column: finance_bill.UPDATE_NAME
     */
    private String updateName;

    /**
     * 业务单号，与BUSS_ORDER_TYPE对应 column: finance_bill.BUSS_ORDER_NUM
     */
    private String bussOrderNum;

    /**
     * 产生业务流水的订单类型：1=提现，2=返佣 column: finance_bill.BUSS_ORDER_TYPE
     */
    private Integer bussOrderType;

    /**
     * 所属哪笔订单 column: finance_bill.ORDER_ID
     */
    private Integer orderId;

    private String addTime;

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

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

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public BigDecimal getGenAmount() {
        return genAmount;
    }

    public void setGenAmount(BigDecimal genAmount) {
        this.genAmount = genAmount;
    }

    public Integer getGenEvent() {
        return genEvent;
    }

    public void setGenEvent(Integer genEvent) {
        this.genEvent = genEvent;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
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

    public String getBussOrderNum() {
        return bussOrderNum;
    }

    public void setBussOrderNum(String bussOrderNum) {
        this.bussOrderNum = bussOrderNum == null ? null : bussOrderNum.trim();
    }

    public Integer getBussOrderType() {
        return bussOrderType;
    }

    public void setBussOrderType(Integer bussOrderType) {
        this.bussOrderType = bussOrderType;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
