package com.fcgo.weixin.persist.po;

import java.util.Date;

public class OperGroupRelationPO {
    /**
     * id
     * column: oper_group_relation.id
     */
    private Integer id;

    /**
     * 运营用户id
     * column: oper_group_relation.user_id
     */
    private Integer userId;

    /**
     * 用户组id
     * column: oper_group_relation.group_id
     */
    private Integer groupId;

    /**
     * 创建人
     * column: oper_group_relation.create_name
     */
    private String createName;

    /**
     * 创建时间
     * column: oper_group_relation.create_time
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}