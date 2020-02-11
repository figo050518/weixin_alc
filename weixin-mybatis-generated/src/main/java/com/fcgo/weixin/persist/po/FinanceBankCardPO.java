package com.fcgo.weixin.persist.po;

import java.util.Date;

public class FinanceBankCardPO {
    /**
     * 主键
     * column: finance_bank_card.ID
     */
    private Integer id;

    /**
     * 是否删除
     * column: finance_bank_card.IS_DELETE
     */
    private Integer isDelete;

    /**
     * 创建时间
     * column: finance_bank_card.CREATE_TIME
     */
    private Date createTime;

    /**
     * 更新时间
     * column: finance_bank_card.UPDATE_TIME
     */
    private Date updateTime;

    /**
     * 银行名称
     * column: finance_bank_card.BANK_NAME
     */
    private String bankName;

    /**
     * 银行卡卡号
     * column: finance_bank_card.CARD_NUM
     */
    private String cardNum;

    /**
     * 开户行名称
     * column: finance_bank_card.BANK_ACCT
     */
    private String bankAcct;

    /**
     * 持卡人姓名
     * column: finance_bank_card.OWNER_NAME
     */
    private String ownerName;

    /**
     * 店主ID
     * column: finance_bank_card.SELLER_ID
     */
    private Integer sellerId;

    /**
     * 更新人
     * column: finance_bank_card.UPDATE_NAME
     */
    private String updateName;

    /**
     * 创建人
     * column: finance_bank_card.CREATE_NAME
     */
    private String createName;

    /**
     * 店铺ID
     * column: finance_bank_card.SHOP_ID
     */
    private Integer shopId;

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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum == null ? null : cardNum.trim();
    }

    public String getBankAcct() {
        return bankAcct;
    }

    public void setBankAcct(String bankAcct) {
        this.bankAcct = bankAcct == null ? null : bankAcct.trim();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
}