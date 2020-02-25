package com.fcgo.weixin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerManager {
    public static Logger getLoginLog(){
        return buildLogByName("loginLog");
    }

    public static Logger getSellerLog(){
        return buildLogByName("sellerOperateLog");
    }

    private static Logger buildLogByName(String name){
        return LoggerFactory.getLogger(name);
    }

    public static Logger getfileUploadLog(){
        return buildLogByName("fileUpload");
    }
}
