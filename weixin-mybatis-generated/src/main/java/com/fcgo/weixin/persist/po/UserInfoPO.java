package com.fcgo.weixin.persist.po;

import java.util.Date;

public class UserInfoPO {
    /**
     * 主键
     * column: user_info.ID
     */
    private Integer id;

    /**
     * 是否删除
     * column: user_info.IS_DELETE
     */
    private Integer isDelete;

    /**
     * 创建时间
     * column: user_info.CREATE_TIME
     */
    private Date createTime;

    /**
     * 更新时间
     * column: user_info.UPDATE_TIME
     */
    private Date updateTime;

    /**
     * 微信ID
     * column: user_info.WX_ID
     */
    private String wxId;

    /**
     * 手机号
     * column: user_info.TEL_NUM
     */
    private String telNum;

    /**
     * 昵称
     * column: user_info.NIKE_NAME
     */
    private String nikeName;

    /**
     * 添加人
     * column: user_info.create_name
     */
    private String createName;

    /**
     * 更新人
     * column: user_info.update_name
     */
    private String updateName;

    /**
     * 1：代表买家 2：代表卖家
     * column: user_info.user_type
     */
    private Integer userType;

    /**
     * 非常购商户门店用户ID
     * column: user_info.fcg_seller_id
     */
    private Integer fcgSellerId;

    /**
     * 非常购token
     * column: user_info.fcg_token
     */
    private String fcgToken;

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

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId == null ? null : wxId.trim();
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum == null ? null : telNum.trim();
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName == null ? null : nikeName.trim();
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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getFcgSellerId() {
        return fcgSellerId;
    }

    public void setFcgSellerId(Integer fcgSellerId) {
        this.fcgSellerId = fcgSellerId;
    }

    public String getFcgToken() {
        return fcgToken;
    }

    public void setFcgToken(String fcgToken) {
        this.fcgToken = fcgToken == null ? null : fcgToken.trim();
    }
}