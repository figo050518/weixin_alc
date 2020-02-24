package com.fcgo.weixin.common.service;

import com.fcgo.weixin.common.constants.OssBucket;
import com.fcgo.weixin.common.exception.ServiceException;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class AliyunOssPictureService {

    final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 产品图片后缀格式限制
     */
    public static final List<String> PRODUCT_IMAGE_EXT_LIST = new ArrayList<String>();

    static {
        PRODUCT_IMAGE_EXT_LIST.add("JPG");
        PRODUCT_IMAGE_EXT_LIST.add("JPEG");
        PRODUCT_IMAGE_EXT_LIST.add("PNG");
        PRODUCT_IMAGE_EXT_LIST.add("GIF");
        PRODUCT_IMAGE_EXT_LIST.add("BMP");
    }

    private static final List<String> legalBucket = Lists.newArrayList(OssBucket.brand,
            OssBucket.product);

    public String updateImage(MultipartFile files,String bucket){
        if (!legalBucket.contains(bucket)){
            logger.warn("updateImage fail, illegal bucket {}", bucket);
            throw new ServiceException(401, "bucket不支持");
        }
        String originalFilename = files.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String tempExt = ext.toUpperCase();
        byte[] filesByte = null;
        try {
            filesByte = files.getBytes();
        } catch (IOException e) {
            throw new ServiceException(401, "图片解析出错");
        }
        InputStream imgInputStream = new ByteArrayInputStream(filesByte);
        // 验证上传的图片信息是否有效
        if (imgInputStream == null) {
            logger.error("update image fail,{} - {}",originalFilename, bucket);
            throw new ServiceException(401, "图片解析出错");
        }
        if (!PRODUCT_IMAGE_EXT_LIST.contains(tempExt)) {
            logger.error("update image fail invalid pic type,{} {}", originalFilename, bucket);
            throw new ServiceException(401, "不是正确的图片类型");
        }
        String picNewName = OssProxyService.createNameByUUID();
        String picUrl = OssProxyService.putImgStreamIntoBucket(bucket, picNewName, imgInputStream);
        return picUrl;
    }
}
