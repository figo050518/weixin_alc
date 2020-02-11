package com.fcgo.weixin.application.impl.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fcgo.weixin.application.interfaces.IImageService;
import com.fcgo.weixin.common.util.RandomGUID;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 * 图片服务接口实现类-七牛云存储实现
 * 
 * @ClassName: QiniuImageServiceImpl
 * @Description: TODO
 * @author mail.chenc
 * @date 2017年4月7日 下午7:46:16
 */
@Service
public class QiniuImageServiceImpl implements IImageService {
    private Logger logger = Logger.getLogger(QiniuImageServiceImpl.class);

    private static UploadManager uploadManager = new UploadManager();
    private static String ak = "koCHS2gjqe4bSzxus9oyk2YZ6_FDqiqIzPvcZ-nv";
    private static String sk = "5SUkLjCgHBbC_Nw8Vl8J8gAck125Z76u-NbPCWqt";
    private static String bk = "product";
    private static String domain = "http://oa6l8rxnr.bkt.clouddn.com/";

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

    @Override
    public String uploadImage(InputStream imgInputStream, String prefix, String suffix) {
        // 验证上传的图片信息是否有效
        if (imgInputStream == null) {
            logger.error("图片文件无效");
            return "invalidImage";
        }
        if (!PRODUCT_IMAGE_EXT_LIST.contains(suffix)) {
            logger.error("不是正确的图片类型");
            return "invalidPreFix";
        }

        // 生成新的图片文件名称，避免名称相同冲突
        String newImgName = this.generateImgFileName(prefix, suffix);
        try {
            Auth auth = Auth.create(ak, sk);
            String token = auth.uploadToken(bk);
            byte[] byteData = IOUtils.toByteArray(imgInputStream);
            Response res = uploadManager.put(byteData, newImgName, token);
            if (res != null && res.statusCode == 200) {
                return domain + newImgName;
            }
        }
        catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        finally {
            if (imgInputStream != null) {
                try {
                    imgInputStream.close();
                }
                catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
        return "error";
    }

    /**
     * 验证是否为图片文件
     * 
     * @param suffix 图片文件后缀
     * @return 是图片文件返回true否则返回false
     */
    private boolean validateImage(String suffix) {
        if (!StringUtils.isEmpty(suffix)) {
            Pattern p =
                    Pattern.compile(
                            "(jpg|bmp|eps|\\gif|\\mif|\\miff|\\png|\\tif|\\tiff|\\svg|\\wmf|\\jpe|\\jpeg|\\dib|\\ico|\\tga|\\cut|\\pic)",
                            Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(suffix);
            return m.find();
        }
        return false;
    }

    /**
     * 生成新的文件名称(格式：prefix+32位随机数+suffix)
     * 
     * @param sourceFileName 原文件名称
     * @return
     */
    private String generateImgFileName(String prefix, String suffix) {
        if (!StringUtils.isEmpty(suffix)) {
            if (!StringUtils.isEmpty(prefix)) {
                return prefix + new RandomGUID().toString() + suffix;
            }
            else {
                return new RandomGUID().toString() + suffix;
            }
        }
        return "";
    }

    // public static void main(String[] args){
    // File file = new File("F:\\timg.jpg");
    // try {
    // InputStream in = new FileInputStream(file);
    // System.out.println(new QiniuImageServiceImpl().uploadImage(in,null,".jpg"));
    // } catch (FileNotFoundException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }

}
