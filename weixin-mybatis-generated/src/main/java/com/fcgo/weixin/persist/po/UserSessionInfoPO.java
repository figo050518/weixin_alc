package com.fcgo.weixin.persist.po;

import java.util.Date;

public class UserSessionInfoPO {
    /**
     * 主键ID
     * column: user_session_info.id
     */
    private Integer id;

    /**
     * 随机生成不重复ID
     * column: user_session_info.token_id
     */
    private String tokenId;

    /**
     * 用户手机号码
     * column: user_session_info.telephone
     */
    private String telephone;

    /**
     * 添加时间
     * column: user_session_info.create_time
     */
    private Date createTime;

    /**
     * 添加人姓名
     * column: user_session_info.create_name
     */
    private String createName;

    /**
     * 修改时间
     * column: user_session_info.update_time
     */
    private Date updateTime;

    /**
     * 修改人姓名
     * column: user_session_info.update_name
     */
    private String updateName;

    /**
     * 是否删除：1：删除，0不删除'
     * column: user_session_info.delete_flag
     */
    private String deleteFlag;

    /**
     * 删除时间
     * column: user_session_info.delete_time
     */
    private Date deleteTime;

    /**
     * 删除人姓名
     * column: user_session_info.delete_name
     */
    private String deleteName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId == null ? null : tokenId.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getDeleteName() {
        return deleteName;
    }

    public void setDeleteName(String deleteName) {
        this.deleteName = deleteName == null ? null : deleteName.trim();
    }
}