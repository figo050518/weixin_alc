package com.fcgo.weixin.common.constants;

/**
 * 售后订单状态
 * 
 * @author Ww
 */
public enum OrdRefundStateType {
    WAIT_DUDIT(1, "申请中"), PASS_ALL(2, "已通过"), FISH_ALL(3, "已完成"), REFUSE(4, "拒绝");

    private OrdRefundStateType(int key, String value) {
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

    public static OrdRefundStateType getValue(int key) {
        for (OrdRefundStateType ordRefundStateType : OrdRefundStateType.values()) {
            if (ordRefundStateType.key == key) {
                return ordRefundStateType;
            }
        }
        return null;
    }

}
