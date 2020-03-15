package com.fcgo.weixin.common.util;

import java.math.BigDecimal;

public class BigDecimalHelper {

    public static String format(double val){
        return new BigDecimal(val)
                .setScale(2,BigDecimal.ROUND_HALF_EVEN)
                .toPlainString();
    }
}
