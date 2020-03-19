package com.fcgo.weixin.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
public enum WalletBizType {
    /**
     * 运费
     */
    DELIVERY_AMOUNT(1);
    @Getter
    private int code;
}
