package com.fcgo.weixin.common.controller;

import com.aliyun.oss.model.Bucket;
import com.fcgo.weixin.common.annotation.IgnoreSession;
import com.fcgo.weixin.common.service.AliyunOssPictureService;
import com.fcgo.weixin.model.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file/upload")
public class UploadController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AliyunOssPictureService aliyunOssPictureService;

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public ApiResponse uploadImage(@RequestParam(value = "imageFileName") MultipartFile files,
                              @RequestParam(value = "bucket", defaultValue = "linkstyle2", required = false) String bucket) {
        logger.info("file upload image, bucket {}",bucket);
        String url = aliyunOssPictureService.updateImage(files, bucket);
        return new ApiResponse.ApiResponseBuilder().code(200)
                .data(url).message("上传图片成功").build();
    }

    //@RequestMapping(value = "/createBucket", method = RequestMethod.GET)
    //@IgnoreSession
    public ApiResponse createBucket(@RequestParam("bucketName")String bucketName){
        Bucket bucket = aliyunOssPictureService.createBucket(bucketName);
        return new ApiResponse.ApiResponseBuilder().code(200)
                .data(bucket).message("create bucket success")
                .build();
    }
}
