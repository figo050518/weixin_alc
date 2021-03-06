package com.fcgo.weixin.application.interfaces;

import java.io.InputStream;

/**
 * 七牛服务接口
 * 
 * @ClassName: IQiniuService
 * @Description: TODO
 * @author mail.chenc
 * @date 2017年4月7日 下午5:12:52
 */
public interface IImageService {
    /**
     * 上传文件
     * 
     * @param imgInputStream 文件输入流
     * @param prefix 图片名称前缀,上传后的图片名称将携带该前缀(可为空)
     * @param suffix 图片文件类型(.png .jpg)
     * @return
     */
    public String uploadImage(InputStream imgInputStream, String prefix, String suffix);
}
