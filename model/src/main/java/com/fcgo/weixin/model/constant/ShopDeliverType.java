package com.fcgo.weixin.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1:自己配送 2:达达配送
 * @author chenchao
 */
@AllArgsConstructor
public enum ShopDeliverType {


    SELF(1),
    DADA(2);

    @Getter
    private int code;


    private static final Map<Integer, ShopDeliverType> cache;
    static {
        cache = Stream.of(values()).collect(Collectors.toMap(ShopDeliverType::getCode, Function.identity()));
    }

    public static ShopDeliverType getDeliverType(Integer code){
        return cache.get(code);
    }
}
