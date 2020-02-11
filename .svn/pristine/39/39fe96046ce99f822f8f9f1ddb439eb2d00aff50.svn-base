package com.fcgo.weixin.common.constants;

/**
 * @author Ww
 */
public enum OrderRefundType {
    // 是否退货
    TUIHUI(1, "退货"), NOTUI(0, "不退"),
    // 是否申请退货
    YES_REFUND(3, "已申请"), NO_REFUND(4, "未申请");
    private OrderRefundType(int key, String value) {
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

    public static OrderRefundType getValue(int key) {
        for (OrderRefundType orderRefundType : OrderRefundType.values()) {
            if (orderRefundType.key == key) {
                return orderRefundType;
            }
        }
        return null;
    }

}
