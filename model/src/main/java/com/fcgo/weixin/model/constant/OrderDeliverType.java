package com.fcgo.weixin.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public enum OrderDeliverType {
    /**
     * 到店自取
     */
    USER_FETCH(1),

    SHOP_DELIVER(2);
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
