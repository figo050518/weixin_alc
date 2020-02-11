package com.fcgo.weixin.persist.po;

import java.util.Date;

public class UserAddressPO {
    /**
     * 主键
     * column: user_address.ID
     */
    private Integer id;

    /**
     * 删除标识
     * column: user_address.IS_DELETE
     */
    private Integer isDelete;

    /**
     * 创建时间
     * column: user_address.CREATE_TIME
     */
    private Date createTime;

    /**
     * 更新时间
     * column: user_address.UPDATE_TIME
     */
    private Date updateTime;

    /**
     * 收货人姓名
     * column: user_address.RECEIVER_NAME
     */
    private String receiverName;

    /**
     * 联系电话
     * column: user_address.CONTACT_NUM
     */
    private String contactNum;

    /**
     * 详细地址
     * column: user_address.DETAIL_ADDR
     */
    private String detailAddr;

    /**
     * 默认收获地址，0=不是默认，1=默认
     * column: user_address.IS_DEFAULT
     */
    private Integer isDefault;

    /**
     * 用户ID
     * column: user_address.USER_ID
     */
    private Integer userId;

    /**
     * 添加人
     * column: user_address.create_name
     */
    private String createName;

    /**
     * 更新人
     * column: user_address.update_name
     */
    private String updateName;

    /**
     * 省
     * column: user_address.province
     */
    private String province;

    /**
     * 市
     * column: user_address.city
     */
    private String city;

    /**
     * 区
     * column: user_address.area
     */
    private String area;

    /**
     * 区的code
     * column: user_address.areaCode
     */
    private String areacode;

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

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum == null ? null : contactNum.trim();
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr == null ? null : detailAddr.trim();
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode == null ? null : areacode.trim();
    }
}