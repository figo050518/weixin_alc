package com.fcgo.weixin.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DelStatus {

    NO((byte)0),YES((byte)1);

    @Getter
    byte code;
}
