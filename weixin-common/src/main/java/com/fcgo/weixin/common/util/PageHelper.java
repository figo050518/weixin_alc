package com.fcgo.weixin.common.util;

/**
 * Created by chao.chen on 2018/11/20.
 */
public class PageHelper {
    private PageHelper() {
    }

    public static int getPageTotal(int total, int pageSize) {
        return total % pageSize == 0 ? total/pageSize: total/pageSize + 1;
    }

    public static int getOffsetOfMysql(int pageNum,int limit){
        if (pageNum<1){
            pageNum = 1;
        }
        return (pageNum - 1) * limit;
    }
}
