package com.fcgo.weixin.model.constant;

import lombok.Getter;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1:收入(充值),2:支出,3:支出回退 4:违约金
 * @author chenchao
 */

public enum BillsInOutType {

    IN((byte)1,"收入","充值"),
    OUT((byte)2,"支出","配送"),
    REFUND_OUT((byte)3,"支出回退","退运费"),
    PENALTY((byte)4,"罚金","违约金");

    BillsInOutType(byte code, String desc, String deliverDesc) {
        this.code = code;
        this.desc = desc;
        this.deliverDesc = deliverDesc;
    }

    @Getter
    private byte code;
    @Getter
    private String desc;
    @Getter
    private String deliverDesc;


    private static final Map<Byte, BillsInOutType> cache;
    static {
        cache = Stream.of(values()).collect(Collectors.toMap(BillsInOutType::getCode, Function.identity()));
    }

    public static BillsInOutType getDeliverType(Byte code){
        if (Objects.isNull(code)){
            return null;
        }
        return cache.get(code);
    }

}
