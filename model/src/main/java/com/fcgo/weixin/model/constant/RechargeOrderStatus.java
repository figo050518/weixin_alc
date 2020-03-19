package com.fcgo.weixin.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
public enum RechargeOrderStatus {
    WAITING_PAY( 0, "未支付"),
    PAID(1, "已支付"),
    REFUND(2, "已退款"),
    CANCELED(3, "取消支付");

    @Getter
    int code;
    @Getter
    String desc;
}
