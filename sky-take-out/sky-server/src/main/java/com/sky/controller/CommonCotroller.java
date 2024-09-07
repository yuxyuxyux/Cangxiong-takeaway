package com.sky.controller;

import com.sky.properties.AliOssProperties;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RequestMapping("/admin/common")
@RestController
public class CommonCotroller {
    @Autowired
    private AliOssProperties aliOssProperties;
//
//    http://localhost/api/common/upload
    Environment environment;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        AliOssUtil aliOssUtil = new AliOssUtil(
        );

        String endpoit = environment.getProperty("sky.alioss.endpoit");
        String bucketName = environment.getProperty("sky.alioss.bucketName");
        InputStream inputStream = file.getInputStream();
        String originalFilename = file.getOriginalFilename();
        String name =   UUID.randomUUID().toString()+originalFilename;
        String upload = AliOssUtil.upload(endpoit,bucketName, name, inputStream);
        return Result.success(upload);
    }
}
