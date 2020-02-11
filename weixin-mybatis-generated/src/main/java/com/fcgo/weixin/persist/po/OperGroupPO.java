package com.fcgo.weixin.persist.po;

import java.util.Date;

public class OperGroupPO {
    /**
     * ID
     * column: oper_group.id
     */
    private Integer id;

    /**
     * 用户组名称
     * column: oper_group.group_name
     */
    private String groupName;

    /**
     * 删除状态1删除0未删除
     * column: oper_group.is_delete
     */
    private Integer isDelete;

    /**
     * 创建人
     * column: oper_group.create_name
     */
    private String createName;

    /**
     * 创建时间
     * column: oper_group.create_time
     */
    private Date createTime;

    /**
     * 更新人
     * column: oper_group.update_name
     */
    private String updateName;

    /**
     * 更新时间
     * column: oper_group.update_time
     */
    private Date updateTime;

    /**
     * 删除人
     * column: oper_group.delete_name
     */
    private String deleteName;

    /**
     * 删除时间
     * column: oper_group.delete_time
     */
    private Date deleteTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleteName() {
        return deleteName;
    }

    public void setDeleteName(String deleteName) {
        this.deleteName = deleteName == null ? null : deleteName.trim();
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}