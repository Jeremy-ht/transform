package com.isoft.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.isoft.utils.ResponseData;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
@Api(tags = "上传文件")
public class UploadFileController {

    // 头像上传
    @PostMapping("/updataFile")
    public ResponseData fileUpdata(MultipartFile file) {

        // 配置文件注入
        String endpoint = "";
        String accessKeyId = "";
        String accessKeySecret = "";
        String bucketName = "";
        try {
            // 获取上传名称
            String filename = file.getOriginalFilename();
            // uuid
            String uuid = UUID.randomUUID().toString();
            // 格式化当前时间
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd/");
            String date = localDate.format(dateTimeFormatter);

            // uuid + filename 文件名
            filename = date + uuid + filename;

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 上传文件流
            InputStream inputStream = file.getInputStream();

            ossClient.putObject(bucketName, filename, inputStream);

            String path = "http://" + bucketName + "." + endpoint + "/" + filename;

            // 关闭OSSClient
            ossClient.shutdown();

            return ResponseData.success().data("location", path);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseData.error().message("图片上传失败");
        }

    }
}
