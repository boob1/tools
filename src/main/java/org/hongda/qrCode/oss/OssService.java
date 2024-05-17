/*
package org.hongda.qrCode.oss;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;

*/
/**
 * @ClassName OssService
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/5/13 19:41
 **//*

@Service
@Slf4j
public class OssService {
    */
/**
     * 后端上传使用
     *//*

    @Value("${oss.backEndPoint}")
    private String backEndPoint;
    @Value("${oss.userAccessKey}")
    private String userAccessKey;
    @Value("${oss.userScretKey}")
    private String userScretKey;
    @Value("${oss.bucketName}")
    private String bucketName;
    @Value("${oss.imgPath}")
    private String imgPath;
    @Value("${oss.videoPath}")
    private String videoPath;
    @Value("${oss.pdfPath}")
    private String pdfPath;
    @Value("${oss.iconPath}")
    private String iconPath;

    private RGWPassport rgwPassport;
    private AmazonS3 s3Client;

    */
/**
     * 默认失效时间（s）
     *//*

    private Long defaltExpireTime = 600L;

    @PostConstruct
    private void init() {
        log.info("初始化oss上传endPoint:{}", backEndPoint);
        log.info("初始化oss上传userAccessKey:{}", userAccessKey);
        log.info("初始化oss上传userScretKey:{}", userScretKey);
        log.info("初始化oss上传bucketName:{}", bucketName);
        rgwPassport = new RGWPassport(backEndPoint, userAccessKey, userScretKey);
        ClientConfiguration conf = new ClientConfiguration();
        conf.setProtocol(Protocol.HTTP);
        conf.setSignerOverride("S3SignerType");
        AWSCredentials credentials = new BasicAWSCredentials(rgwPassport.getAccessKey(), rgwPassport.getSecretKey());
        s3Client = new AmazonS3Client(credentials, conf);
        s3Client.setEndpoint(rgwPassport.getEndPoint());
        log.info("OSS初始化完成");
    }


    */
/**
     * 上传单个文件(通用)
     *
     * @param file      文件
     * @param preDir   oss路径
     * @param limitFlag 访问受限(0:不受限 1:受限)
     * @return
     *//*

    public String uploadFileOss(MultipartFile file, String preDir, String limitFlag) {
        ObjectMetadata metadata = new ObjectMetadata();
        String fileName = getOssFileName() + "." + getFileSuffix(file.getOriginalFilename());
        try {
            log.info("上传文件名{}，上传文件大小{}，上传文件路径{}：", file.getName(), file.getSize(), preDir);
            metadata.addUserMetadata("x-ycore-filename", URLEncoder.encode(file.getName(), "UTF-8"));
            InputStream is = file.getInputStream();
            s3Client.putObject(bucketName, StrUtil.format("{}{}", preDir, fileName), is, metadata);
            S3Object obj = s3Client.getObject(bucketName, StrUtil.format("{}{}", preDir, fileName));

            // 访问权限设置
            if (limitFlag.equals("0")) {
                AccessControlList acl = s3Client.getObjectAcl(bucketName, obj.getKey());
                acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
                s3Client.setObjectAcl(bucketName, obj.getKey(), acl);
            }

            log.info("oss文件存储路径{}", obj.getKey());
            is.close();
            obj.close();
            return obj.getKey();
        } catch (Exception e) {
            log.error("oss上传文件出错", e);
            e.printStackTrace();
        }
        return "";
    }
}
*/
