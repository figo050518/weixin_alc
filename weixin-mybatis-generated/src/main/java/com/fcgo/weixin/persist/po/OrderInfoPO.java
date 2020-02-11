package com.fcgo.weixin.persist.po;

import java.math.BigDecimal;
import java.util.Date;

public class OrderInfoPO {
    /**
     * 主键自增
     * column: ord_order_info.ID
     */
    private Integer id;

    /**
     * 删除状态0代表未删除1代表已删除
     * column: ord_order_info.IS_DELETE
     */
    private Integer isDelete;

    /**
     * 添加时间
     * column: ord_order_info.CREATE_TIME
     */
    private Date createTime;

    /**
     * 修改时间
     * column: ord_order_info.UPDATE_TIME
     */
    private Date updateTime;

    /**
     * 订单编号
     * column: ord_order_info.ORDER_NUM
     */
    private String orderNum;

    /**
     * 订单状态：0=待付款,1=待发货,2=待收货,3=已完成,4=已关闭
     * column: ord_order_info.ORDER_STATE
     */
    private Integer orderState;

    /**
     * 支付方式
     * column: ord_order_info.PAY_WAY
     */
    private String payWay;

    /**
     * 支付时间
     * column: ord_order_info.PAY_TIME
     */
    private Date payTime;

    /**
     * 发货时间
     * column: ord_order_info.SEND_TIME
     */
    private Date sendTime;

    /**
     * 买家ID
     * column: ord_order_info.USER_ID
     */
    private Integer userId;

    /**
     * 卖家ID
     * column: ord_order_info.SELLER_ID
     */
    private Integer sellerId;

    /**
     * 店铺ID
     * column: ord_order_info.SHOP_ID
     */
    private Integer shopId;

    /**
     * 实际支付金额
     * column: ord_order_info.ACT_PAY_AMOUNT
     */
    private BigDecimal actPayAmount;

    /**
     * 订单运费
     * column: ord_order_info.FREIGHT_AMOUNT
     */
    private BigDecimal freightAmount;

    /**
     * 商品总额，不含运费
     * column: ord_order_info.PRO_AMOUNT
     */
    private BigDecimal proAmount;

    /**
     * 折扣
     * column: ord_order_info.ORD_DISCOUNT
     */
    private BigDecimal ordDiscount;

    /**
     * 物流单号
     * column: ord_order_info.LOGISTICS_NUM
     */
    private String logisticsNum;

    /**
     * 物流公司
     * column: ord_order_info.LOGISTICS_COMP
     */
    private String logisticsComp;

    /**
     * 买家备注
     * column: ord_order_info.SELLER_REMARKS
     */
    private String sellerRemarks;

    /**
     * 订单关闭原因
     * column: ord_order_info.CLOSE_DESC
     */
    private String closeDesc;

    /**
     * 评价与否:0=未评价，1=已评价
     * column: ord_order_info.IS_EALUATE
     */
    private Integer isEaluate;

    /**
     * 订单完成时间
     * column: ord_order_info.FINISH_TIME
     */
    private Date finishTime;

    /**
     * 订单取消时间
     * column: ord_order_info.CANCEL_TIME
     */
    private Date cancelTime;

    /**
     * 添加人员
     * column: ord_order_info.CREATE_NAME
     */
    private String createName;

    /**
     * 修改人员
     * column: ord_order_info.UPDATE_NAME
     */
    private String updateName;

    /**
     * 删除人员
     * column: ord_order_info.DEL_NAME
     */
    private String delName;

    /**
     * 删除时间
     * column: ord_order_info.DEL_TIME
     */
    private Date delTime;

    /**
     * 1：自营订单 2：非常购订单
     * column: ord_order_info.order_type
     */
    private Integer orderType;

    /**
     * 1：用户自己取消 2：卖家取消  3：系统取消
     * column: ord_order_info.CANCEL_TYPE
     */
    private Integer cancelType;

    /**
     * 主订单的ID号
     * column: ord_order_info.parent_order_id
     */
    private Integer parentOrderId;

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

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public BigDecimal getActPayAmount() {
        return actPayAmount;
    }

    public void setActPayAmount(BigDecimal actPayAmount) {
        this.actPayAmount = actPayAmount;
    }

    public BigDecimal getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(BigDecimal freightAmount) {
        this.freightAmount = freightAmount;
    }

    public BigDecimal getProAmount() {
        return proAmount;
    }

    public void setProAmount(BigDecimal proAmount) {
        this.proAmount = proAmount;
    }

    public BigDecimal getOrdDiscount() {
        return ordDiscount;
    }

    public void setOrdDiscount(BigDecimal ordDiscount) {
        this.ordDiscount = ordDiscount;
    }

    public String getLogisticsNum() {
        return logisticsNum;
    }

    public void setLogisticsNum(String logisticsNum) {
        this.logisticsNum = logisticsNum == null ? null : logisticsNum.trim();
    }

    public String getLogisticsComp() {
        return logisticsComp;
    }

    public void setLogisticsComp(String logisticsComp) {
        this.logisticsComp = logisticsComp == null ? null : logisticsComp.trim();
    }

    public String getSellerRemarks() {
        return sellerRemarks;
    }

    public void setSellerRemarks(String sellerRemarks) {
        this.sellerRemarks = sellerRemarks == null ? null : sellerRemarks.trim();
    }

    public String getCloseDesc() {
        return closeDesc;
    }

    public void setCloseDesc(String closeDesc) {
        this.closeDesc = closeDesc == null ? null : closeDesc.trim();
    }

    public Integer getIsEaluate() {
        return isEaluate;
    }

    public void setIsEaluate(Integer isEaluate) {
        this.isEaluate = isEaluate;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
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

    public String getDelName() {
        return delName;
    }

    public void setDelName(String delName) {
        this.delName = delName == null ? null : delName.trim();
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getCancelType() {
        return cancelType;
    }

    public void setCancelType(Integer cancelType) {
        this.cancelType = cancelType;
    }

    public Integer getParentOrderId() {
        return parentOrderId;
    }

    public void setParentOrderId(Integer parentOrderId) {
        this.parentOrderId = parentOrderId;
    }
}