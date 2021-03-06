package com.fcgo.weixin.common.codec;

/*
 * Copyright 2013 Focus Technology, Co., Ltd. All rights reserved.
 */
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * DESEncrypterComponent.java
 * 
 * @author xiahan
 */
public class MD5EncrypterUtil {

    private static final Log logger = LogFactory.getLog(MD5EncrypterUtil.class);

    /**
     * MD5密码加密,只用于密码加密
     * 
     * @param message
     * @return
     */
    public static String md5PwdEncrypt(String message) {
        try {
            return DigestUtils.md5Hex(message);
        }
        catch (Exception ex) {
            logger.error("加密用户密码异常", ex);
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("111111"));
    }

}
