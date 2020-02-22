package com.fcgo.weixin.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public enum PrdShelfStatus {

    INIT(0),
    ON(1),
    OFF(2);

    @Getter
    private int code;

    private static  final Map<Integer,PrdShelfStatus> cache;
    static {
        cache = Stream.of(values()).collect(Collectors.toMap(PrdShelfStatus::getCode, Function.identity()));
    }

    public static PrdShelfStatus getStatus(int code){
        return cache.get(code);
    }
}
