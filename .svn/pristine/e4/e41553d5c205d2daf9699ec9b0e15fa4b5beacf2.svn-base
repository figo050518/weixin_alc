package com.fcgo.weixin.common.constants;

/**
 * 售后状态 
 * 
 * @author guangyang
 */
public enum RefundStatusConstants {
    APPLY(1, "申请中"), PASS(2, "已通过"), FINISHED(3, "已完成"), REFUSE(4, "拒绝");

    private RefundStatusConstants(int key, String value) {
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

    public static RefundStatusConstants getValue(int key) {
        for (RefundStatusConstants orderStateConstants : RefundStatusConstants.values()) {
            if (orderStateConstants.key == key) {
                return orderStateConstants;
            }
        }
        return null;
    }

}
