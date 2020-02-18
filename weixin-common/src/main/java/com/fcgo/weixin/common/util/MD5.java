package com.fcgo.weixin.common.util;

import com.google.common.base.Charsets;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    private static final Logger logger = LoggerFactory.getLogger(MD5.class);

    public MD5() {
    }

    public static String md5(String message) {
        return new String(Hex.encodeHex(md5Digest(message)));
    }

    private static byte[] md5Digest(String message) {
        byte[] md5Bytes = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md5Bytes = md.digest(message.getBytes(Charsets.UTF_8));
        } catch (NoSuchAlgorithmException var3) {
            logger.error("md5 error: NoSuchAlgorithmException");
        }

        return md5Bytes;
    }
}
