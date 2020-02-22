package com.fcgo.weixin.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public enum PrdAuditStatus {
    INIT(0),
    PASS(1),
    REJECT(2);

    @Getter
    private int code;

    private static  final Map<Integer,PrdAuditStatus> cache;
    static {
        cache = Stream.of(values()).collect(Collectors.toMap(PrdAuditStatus::getCode, Function.identity()));
    }

    public static PrdAuditStatus getStatus(int code){
        return cache.get(code);
    }
}
