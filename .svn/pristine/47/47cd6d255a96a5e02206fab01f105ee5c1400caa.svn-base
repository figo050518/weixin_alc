package com.fcgo.weixin.common.constants;

/**
 * @ClassName: UserType
 * @Description:支付方式
 * @author zhonghui.geng
 * @date 2017年4月5日 下午2:54:36
 */
public enum PayWayType {
    WEIXIN(0, "微信");

    private PayWayType(int key, String value) {
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

    public static PayWayType getValue(int key) {
        for (PayWayType orderStateConstants : PayWayType.values()) {
            if (orderStateConstants.key == key) {
                return orderStateConstants;
            }
        }
        return null;
    }

}
