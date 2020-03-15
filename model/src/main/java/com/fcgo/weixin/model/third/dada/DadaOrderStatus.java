package com.fcgo.weixin.model.third.dada;

import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 订单状态(
 * 待接单＝1,
 * 待取货＝2,
 * 配送中＝3,
 * 已完成＝4,
 * 已取消＝5,
 * 已过期＝7,
 * 指派单=8,
 * 妥投异常之物品返回中=9,
 * 妥投异常之物品返回完成=10,
 * 骑士到店=100,
 * 创建达达运单失败=1000
 * 可参考文末的状态说明）
 */
public enum DadaOrderStatus {
    WAITING_ACCEPT(1),
    WAITING_FETCH(2),
    DELIVER_ON_WAY(3),
    FINISH(4),
    CACELED(5),
    EXPIRED(7),
    ASSIGN(8),
    RECALL_GOODS_DELIVERED_FAIL(9),
    RECALL_FINISH_DELIVERED_FAIL(10),
    DRIVER_REACH_SHOP(100),
    CREATE_FAIL(1000);

    @Getter
    private int code;

    DadaOrderStatus(int code) {
        this.code = code;
    }

    private static final Map<Integer,DadaOrderStatus> cache;
    static {
        cache = Stream.of(values()).collect(Collectors.toMap(DadaOrderStatus::getCode, Function.identity()));
    }
    public static DadaOrderStatus getDadaOrderStatus(Integer code){
        return cache.get(code);
    }
 }
