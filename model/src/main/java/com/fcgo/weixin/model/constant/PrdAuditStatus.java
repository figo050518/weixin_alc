package com.fcgo.weixin.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PrdAuditStatus {
    INIT(0),
    PASS(1),
    REJECT(2);

    @Getter
    private int code;
}
