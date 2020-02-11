package com.fcgo.weixin.persist.po;

import java.math.BigDecimal;
import java.util.Date;

public class FinanceWithdrawApplyPO {
    /**
     * 主键 column: finance_withdraw_apply.ID
     */
    private Integer id;

    /**
     * 创建时间 column: finance_withdraw_apply.CREATE_TIME
     */
    private Date createTime;

    /**
     * 更新时间 column: finance_withdraw_apply.UPDATE_TIME
     */
    private Date updateTime;

    /**
     * 删除标识：0=未删除，1=已删除 column: finance_withdraw_apply.IS_DELETE
     */
    private Integer isDelete;

    /**
     * 店主ID column: finance_withdraw_apply.SELLER_ID
     */
    private Integer sellerId;

    /**
     * 提现单号 column: finance_withdraw_apply.WITHDRAW_NUM
     */
    private String withdrawNum;

    /**
     * 店铺ID column: finance_withdraw_apply.SHOP_ID
     */
    private Integer shopId;

    /**
     * 银行卡类型 column: finance_withdraw_apply.CARD_TYPE
     */
    private String cardType;

    /**
     * 卡号 column: finance_withdraw_apply.CARD_NUM
     */
    private String cardNum;

    /**
     * 持卡人姓名 column: finance_withdraw_apply.CARD_OWNER_NAME
     */
    private String cardOwnerName;

    /**
     * 申请提现金额 column: finance_withdraw_apply.WITHDRAW_AMOUNT
     */
    private BigDecimal withdrawAmount;

    /**
     * 审核时间 column: finance_withdraw_apply.AUDIT_TIME
     */
    private Date auditTime;

    /**
     * 审核状态，1：代表审核中 2：提现失败 3：提现成功 column: finance_withdraw_apply.AUDIT_STATE
     */
    private Integer auditState;

    /**
     * 审核建议 column: finance_withdraw_apply.AUDIT_RES
     */
    private String auditRes;

    /**
     * 添加人 column: finance_withdraw_apply.CREATE_NAME
     */
    private String createName;

    /**
     * 更新人 column: finance_withdraw_apply.UPDATE_NAME
     */
    private String updateName;

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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getWithdrawNum() {
        return withdrawNum;
    }

    public void setWithdrawNum(String withdrawNum) {
        this.withdrawNum = withdrawNum == null ? null : withdrawNum.trim();
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum == null ? null : cardNum.trim();
    }

    public String getCardOwnerName() {
        return cardOwnerName;
    }

    public void setCardOwnerName(String cardOwnerName) {
        this.cardOwnerName = cardOwnerName == null ? null : cardOwnerName.trim();
    }

    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Integer getAuditState() {
        return auditState;
    }

    public void setAuditState(Integer auditState) {
        this.auditState = auditState;
    }

    public String getAuditRes() {
        return auditRes;
    }

    public void setAuditRes(String auditRes) {
        this.auditRes = auditRes == null ? null : auditRes.trim();
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
