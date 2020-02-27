package com.fcgo.weixin.common.util;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class SHA256 {
    private final static Logger logger = LoggerFactory.getLogger(SHA256.class);
    private static final String ALC = "alc";

    public static String getAccessCode() {
        String today  = DateUtil.format(new Date(), DateUtil.Format_yyyy_MM_dd);
        return getSHA256Str(today + ALC);
    }

    public static String getSHA256Str(String str){
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            logger.warn("getSHA256Str fail {}",str, e);
        } catch (UnsupportedEncodingException e) {
            logger.warn("getSHA256Str fail {}",str, e);
        }
        return encdeStr;
    }

    public static void main(String[] args) {
        System.out.println(SHA256.getAccessCode());
    }
}
