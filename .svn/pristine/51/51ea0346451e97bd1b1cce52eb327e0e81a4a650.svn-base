package com.fcgo.weixin.controller.interfaces;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fcgo.weixin.application.interfaces.IImageService;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("/uc/interfaces/image")
public class ImageController {

    @Autowired
    private IImageService imageService;

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public @ResponseBody
    String uploadImage(@RequestParam(value = "imageFileName") MultipartFile files) throws IOException {
        String originalFilename = files.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String tempExt = ext.toUpperCase();
        byte[] filesByte = files.getBytes();
        InputStream sbs = new ByteArrayInputStream(filesByte);
        return imageService.uploadImage(sbs, null, tempExt);
    }
    
    @RequestMapping(value="ueUpload",method=RequestMethod.POST)
  	@ResponseBody
  	public Map<String,Object> testUpload(@RequestParam(value = "upfile", required = false) MultipartFile file,HttpServletRequest request, HttpServletResponse response) throws Exception{
    	Map<String,Object> result =Maps.newHashMap();
    	 String originalFilename = file.getOriginalFilename();
         String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
         String tempExt = ext.toUpperCase();
         byte[] filesByte = file.getBytes();
         InputStream sbs = new ByteArrayInputStream(filesByte);
         String url = imageService.uploadImage(sbs, null, tempExt);
         result.put("name", file.getOriginalFilename());//新的文件名
         result.put("originalName", file.getOriginalFilename());//原始文件名
         result.put("size", file.getSize());
         result.put("state", "SUCCESS");
	     result.put("url",url);//展示图片的请求url
  		return result;
  		
  	}
}
