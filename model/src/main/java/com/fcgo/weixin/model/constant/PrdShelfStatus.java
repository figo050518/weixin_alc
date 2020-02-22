package com.fcgo.weixin.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PrdShelfStatus {

    INIT(0),
    ON(1),
    OFF(2);

    @Getter
    private int code;
}
