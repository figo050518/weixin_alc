package com.fcgo.weixin.common.constants;

/**
 * @ClassName: UserType
 * @Description: 用户类型
 * @author zhonghui.geng
 * @date 2017年4月5日 下午2:54:36
 */
public enum UserType {
    BUYER(1, "买家"), SELLER(2, "卖家");

    private UserType(int key, String value) {
        this.key = key;
        this.value = value;
    }

    private String value;
    private int key;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public static UserType getValue(int key) {
        for (UserType orderStateConstants : UserType.values()) {
            if (orderStateConstants.key == key) {
                return orderStateConstants;
            }
        }
        return null;
    }

}
