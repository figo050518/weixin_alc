package com.fcgo.weixin.common.util;

import java.math.BigDecimal;

public class BigDecimalHelper {

    public static String format(double val){
        return keepSimple(new BigDecimal(val))
                .toPlainString();
    }

    public static BigDecimal keepSimple(BigDecimal num){
        return num.setScale(2,BigDecimal.ROUND_HALF_EVEN);
    }

    public static BigDecimal sub(BigDecimal prefix,BigDecimal suffix){
        return keepSimple(prefix.subtract(suffix));
    }
}
