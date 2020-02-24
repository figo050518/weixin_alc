package com.fcgo.weixin.common.controller;

import com.fcgo.weixin.common.service.AliyunOssPictureService;
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
    public String uploadImage(@RequestParam(value = "imageFileName") MultipartFile files,
                              @RequestParam("bucket") String bucket) {
        logger.info("file upload image, bucket {}",bucket);
        return aliyunOssPictureService.updateImage(files, bucket);
    }
}
