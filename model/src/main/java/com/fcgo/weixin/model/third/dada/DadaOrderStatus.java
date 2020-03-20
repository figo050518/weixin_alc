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
    WAITING_ACCEPT(1){
        @Override
        public String getDesc() {
            return "待接单";
        }
    },
    WAITING_FETCH(2){
        @Override
        public String getDesc() {
            return "待取货";
        }
    },
    DELIVER_ON_WAY(3){
        @Override
        public String getDesc() {
            return "配送中";
        }
    },
    FINISH(4){
        @Override
        public String getDesc() {
            return "已完成";
        }
    },
    CACELED(5){
        @Override
        public String getDesc() {
            return "已取消";
        }
    },
    EXPIRED(7){
        @Override
        public String getDesc() {
            return "已过期";
        }
    },
    ASSIGN(8){
        @Override
        public String getDesc() {
            return "指派单";
        }
    },
    RECALL_GOODS_DELIVERED_FAIL(9){
        @Override
        public String getDesc() {
            return "妥投异常之物品返回中";
        }
    },
    RECALL_FINISH_DELIVERED_FAIL(10){
        @Override
        public String getDesc() {
            return "妥投异常之物品返回完成";
        }
    },
    DRIVER_REACH_SHOP(100){
        @Override
        public String getDesc() {
            return "骑士到店";
        }
    },
    CREATE_FAIL(1000){
        @Override
        public String getDesc() {
            return "创建达达运单失败";
        }
    },;

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
    public abstract String getDesc();
 }
