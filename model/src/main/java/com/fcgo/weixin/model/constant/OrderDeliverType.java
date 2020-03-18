package com.fcgo.weixin.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 订单类型 0 外送（方式未定）1 到店取餐 2 商家配送 3 达达配送
 */
@AllArgsConstructor
public enum OrderDeliverType {
    /**
     * 到店自取
     */
    USER_FETCH(1),
    /**
     * 0 外送（方式未定）
     */
    DELIVER(0),
    SHOP_DELIVER(2),
    DADA_DELIVER(3);
    @Getter
    private int code;
    private static final Map<Integer, OrderDeliverType> cache;
    static {
        cache = Stream.of(values()).collect(Collectors.toMap(OrderDeliverType::getCode, Function.identity()));
    }

    public static OrderDeliverType getDeliverType(Integer code){
        if (Objects.isNull(code)){
            return null;
        }
        return cache.get(code);
    }
}
