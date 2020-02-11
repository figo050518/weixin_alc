package com.fcgo.weixin.persist.po;

import java.util.Date;

public class UserLoginPO {
    /**
     * 主键
     * column: user_login.id
     */
    private Integer id;

    /**
     * 手机号
     * column: user_login.telephone
     */
    private String telephone;

    /**
     * 密码
     * column: user_login.password
     */
    private String password;

    /**
     * 用户信息ID
     * column: user_login.user_id
     */
    private Integer userId;

    /**
     * 微信ID
     * column: user_login.wx_id
     */
    private String wxId;

    /**
     * 创建时间
     * column: user_login.create_time
     */
    private Date createTime;

    /**
     * 更新时间
     * column: user_login.update_time
     */
    private Date updateTime;

    /**
     * 删除标识
     * column: user_login.is_delete
     */
    private Integer isDelete;

    /**
     * 添加人
     * column: user_login.create_name
     */
    private String createName;

    /**
     * 更新人
     * column: user_login.update_name
     */
    private String updateName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId == null ? null : wxId.trim();
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