package com.fcgo.weixin.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderNumberUtil {
    /**
     * 生成订单号，规则是当天时间，从年到分，在加4为随机的整数
     * 
     * @return
     */

    // public static Map<String, List<String>> ordNoMap = new HashMap<String, List<String>>();

    public static String generateOrderNo() {

        StringBuilder num = new StringBuilder();
        num.insert(0, generateNumber(4));
        num.insert(0, new SimpleDateFormat("mm").format(new Date()));
        num.insert(0, new SimpleDateFormat("HH").format(new Date()));
        num.insert(0, new SimpleDateFormat("dd").format(new Date()));
        num.insert(0, new SimpleDateFormat("MM").format(new Date()));
        num.insert(0, new SimpleDateFormat("yy").format(new Date()));

        return num.toString();

    }

    private static String generateNumber(int n) {
        if (n < 1 || n > 10) {
            throw new IllegalArgumentException("cannot random " + n + " bit number");
        }
        Random ran = new Random();
        if (n == 1) {
            return String.valueOf(ran.nextInt(10));
        }
        int bitField = 0;
        char[] chs = new char[n];
        for (int i = 0; i < n; i++) {
            while (true) {
                int k = ran.nextInt(10);
                if ((bitField & (1 << k)) == 0) {
                    bitField |= 1 << k;
                    chs[i] = (char) (k + '0');
                    break;
                }
            }
        }
        return new String(chs);
    }

    public static void main(String[] args) {
        System.out.println(generateOrderNo());
    }

}
