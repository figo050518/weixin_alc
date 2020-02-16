package com.fcgo.weixin.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum AccountStatus {
    USEFUL((byte)1),
    USELESS((byte)0);

    @Getter
    private byte code;

}
