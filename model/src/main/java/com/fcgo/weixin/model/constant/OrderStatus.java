package com.fcgo.weixin.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态：
 * 0 等待商家确认
 * 1 已接单
 * 2做酒完成
 * 3配送中
 * 4 订单完成
 * 5 已取消
 * 6 支付后商家取消
 */
@AllArgsConstructor
public enum OrderStatus {
    WAITING_CONFIRM(0),
    RECEIVED(1),
    MAKE_SUCCESS(2),
    DELIVER(3),
    DONE(4),
    BUYER_CANCEL(5),
    SELLER_PLAY_BUYER(6);

    @Getter
    private int code;


}