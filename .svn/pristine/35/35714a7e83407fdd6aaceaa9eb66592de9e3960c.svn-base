/*
 * Copyright 2012 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.fcgo.weixin.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
* @ClassName: DataUtil 
* @Description: 数据转换工具 
* @author zhonghui.geng
* @date 2017年4月7日 下午2:51:46 
*
 */
public abstract class DataUtil {
    /**
     * 将对象转化为字节数组
     * @param object
     * @return
     * @throws IOException
     */
    public static byte[] objectToBytes(Object object) throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        byte[] bytes = baos.toByteArray();
        baos.close();
        oos.close();
        return bytes;
    }
    
    /**
     * 将字节数组转化为对象
     * @author lichmama
     * @param bytes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public static <T> T bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object object = ois.readObject();
        bais.close();
        ois.close();
        return (T) object;
    }
}
