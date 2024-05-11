package org.hongda.upload;

import cn.hutool.core.util.StrUtil;
import org.hongda.vo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @ClassName FileUploadController
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/5/5 11:55
 **/
@RestController
@RequestMapping("/file")
public class FileUploadController {
    @Value("${upload.folder}")
    private String root;
    @Value("${upload.download}")
    private String download;


    @PostMapping("/upload")
    public Result fileUpload(MultipartFile file, HttpServletRequest request) {
        // 判断存放目录是否存在，不存在则创建
        File folder = new File(root);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        // 获取文件名称
        String originalName = file.getOriginalFilename();
        // 获取文件后缀
        String suffix = originalName.substring(originalName.lastIndexOf('.') + 1);
        // 保存文件对象，uuid防止文件重名
        String strNewFileName = StrUtil.format("{}{}{}", UUID.randomUUID().toString().replaceAll("-", ""), ".", suffix);
        try {
            // 上传文件
            // 如果需要上传数据库可以在此处稍作处理
            file.transferTo(new File(folder, strNewFileName));
            // 将文件下载地址或预览地址直接返回给前端，前端可以直接将文件进行预览
            String url =  StrUtil.format("{}{}", root, strNewFileName);

            return Result.success(url);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.failure(e.getMessage(), 500);
        }
    }
}
