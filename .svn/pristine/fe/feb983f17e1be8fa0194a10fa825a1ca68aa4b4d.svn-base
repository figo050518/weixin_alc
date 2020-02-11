package com.fcgo.weixin.common.constants;

/**
 * 订单状态
 * 
 * @author xiahan
 */
public enum OrderStateConstants {
    WAIT_PAY(0, "等待付款"), WAIT_SHIP(1, "等待发货"), WAIT_CONFIRM(2, "等待收货"), FINISHED(3, "已完成"), CANCELED(4, "已取消");

    private OrderStateConstants(int key, String value) {
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

    public static OrderStateConstants getValue(int key) {
        for (OrderStateConstants orderStateConstants : OrderStateConstants.values()) {
            if (orderStateConstants.key == key) {
                return orderStateConstants;
            }
        }
        return null;
    }

}
