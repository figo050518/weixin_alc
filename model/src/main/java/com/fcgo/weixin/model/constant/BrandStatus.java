package com.fcgo.weixin.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BrandStatus {
    USELESS((short)0),
    USEFUL((short)1);

    @Getter
    private short code;
}
