package com.fcgo.weixin.convert;

import com.fcgo.weixin.common.util.DateUtil;

public class BaseConvert {
    public static String fmtDateFromUnixtime(Integer datetime){
        return DateUtil.getDateStrFromUnixTime(datetime,DateUtil.Format_yyyy_MM_dd_HH_mm_ss);
    }
}
