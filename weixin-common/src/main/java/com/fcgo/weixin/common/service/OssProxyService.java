package com.fcgo.weixin.common.service;

import com.aliyun.oss.*;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.fcgo.weixin.model.constant.PictureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class OssProxyService {
    private final static Logger log = LoggerFactory.getLogger(OssProxyService.class);

    private static String END_POINT = "http://oss-cn-hangzhou.aliyuncs.com";
    private static String ACCESS_KEY_ID = "LTAI4FrEVoQgS9xXsz8qmkZF";
    private static String ACCESS_KEY_SECRET = "haXYQY7KU3FF3VCzMsnoARSjnAhofZ";

    public static final String DOMAIN = "linkstyle2.oss-cn-hangzhou.aliyuncs.com";

    public static String createNameByUUID(){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }

    public static String put(String pic, String picType) throws IOException {

//        File file = new File("C:\\test.txt");
        String uuid = createNameByUUID();
        byte[] bytes = new BASE64Decoder().decodeBuffer(pic.replace(" ", "+"));
        InputStream in = new ByteArrayInputStream(bytes);
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(bytes.length);
        meta.setContentType("image/jpeg");// 标记文件类型
        OSS ossClient = getOssClient();
        ossClient.putObject(DOMAIN, uuid + picType, in, meta);

        return "http://"+DOMAIN+"/" + uuid + picType;
    }

    private static class ClientProxy{
        private static OSS ossClient = new OSSClientBuilder().build(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        OSS getOssClient(){
            return ossClient;
        }
    }

    static OSS getOssClient(){
        return new ClientProxy().getOssClient();
    }

    /**
     *
     * @param bucket
     * @param file
     * @param contentType eg:application/vnd.android.package-archive
     * @return
     */
    public static String putFileIntoBucket(String bucket,
                                           MultipartFile file,
                                           String contentType) {
        // 创建OSSClient实例。
        OSS ossClient = getOssClient();
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
        String newName = name + System.currentTimeMillis() + ext;
        ensureBucket(ossClient, bucket);
        setBucketPublicReadable(ossClient, bucket);
        ObjectMetadata content = new ObjectMetadata();
        content.setContentLength(file.getSize());
        content.setContentType(contentType);
        try {
            ossClient.putObject(bucket, newName, file.getInputStream(), content);
            return newName;
        } catch (OSSException ex) {
            log.error("putFileIntoBucket occur OSSException",ex);
        } catch (ClientException ce) {
            log.error("putFileIntoBucket occur ClientException",ce);
        } catch (IOException ce) {
            log.error("putFileIntoBucket occur IOException",ce);
        } finally {
            // 关闭OSS服务，一定要关闭
            ossClient.shutdown();
        }
        return null;
    }


    public static String putFileIntoBucket(String bucket,
                                           InputStream in,
                                           String imageName,
                                           long fileSize,
                                           String ext) {
        // 创建OSS客户端
        OSS ossClient = getOssClient();
        String newName = String.valueOf(System.currentTimeMillis()) + imageName + ext;
        ObjectMetadata content = new ObjectMetadata();
        content.setContentLength(fileSize);
        content.setContentType("image/jpeg");
        PutObjectResult res = ossClient.putObject(bucket, newName, in, content);
        return newName;
    }
    public static String buildImgUrl(String name,String type){

        return new StringBuilder("http://")
                .append(DOMAIN)
                .append("/")
                .append(name)
                .append(".")
                .append(type)
                .toString();
    }

    public static String putImgStreamIntoBucket(String bucket,
                                                String name,
                                                String type,
                                                InputStream in)  {

        OSS ossClient = getOssClient();
        try {
            PictureType pictureType = PictureType.getPictureType(type);
            ObjectMetadata content = new ObjectMetadata();
            content.setContentLength(in.available());
            content.setContentDisposition("inline");
            content.setContentType(pictureType.getMediaType());
            ossClient.putObject(bucket, name + "." + type, in, content);
            String absUrl = buildImgUrl(name, type);
            log.info("update img success, {}",absUrl);
            return absUrl;
        } catch (OSSException oe) {
            log.error("putImgStreamIntoBucket occur OSSException",oe);
        } catch (ClientException ce) {
            log.error("putImgStreamIntoBucket occur ClientException",ce);
        } catch (IOException ex) {
            log.error("putImgStreamIntoBucket occur IOException", ex);
        } finally {
            // 关闭OSS服务，一定要关闭
            ossClient.shutdown();
        }
        return null;
    }

    public static Bucket createBucket( String bucketName){
        OSS ossClient = getOssClient();
        return ensureBucket(ossClient, bucketName );
    }
    // 创建Bucket.
    private static Bucket ensureBucket(OSS client, String bucketName)
            throws OSSException, ClientException {
        Bucket bucket = null;
        try {
            // 创建bucket
            bucket = client.createBucket(bucketName);
            log.info("createBucket successful,bucketName {}", bucketName);
        } catch (ServiceException e) {
            if (!OSSErrorCode.BUCKET_ALREADY_EXISTS.equals(e.getErrorCode())) {
                // 如果Bucket已经存在，则忽略
                throw e;
            }
        }finally {
            return bucket;
        }
    }

    // 把Bucket设置为所有人可读
    private static void setBucketPublicReadable(OSS client, String bucketName)
            throws OSSException, ClientException {
        //设置bucket的访问权限，public-read-write权限
        client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
    }

    public static void copyObject(String appBucket, String srcName, String appName) {
        OSS ossClient = getOssClient();
        ensureBucket(ossClient, appBucket);
        setBucketPublicReadable(ossClient, appBucket);
        ossClient.copyObject(appBucket, srcName, appBucket, appName);
    }
}
