package com.fcgo.weixin.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 平台转账达达状态 0:待充值 1:充值中 2:已充值
 */
@AllArgsConstructor
public enum PlatformRechargeStatus {
    WAITING(0),
    TRYING(1),
    FINISH(2);

    @Getter
    private int code;

    private static final Map<Integer,PlatformRechargeStatus> cache;
    static {
        cache = Stream.of(values()).collect(Collectors.toMap(PlatformRechargeStatus::getCode, Function.identity()));
    }

    public static PlatformRechargeStatus getOrderStatus(Integer code){
        return cache.get(code);
    }
}
