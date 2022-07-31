package com.lypc.hxs.utils;

import cn.hutool.core.util.StrUtil;
import com.lypc.hxs.constant.StatusCode;
import com.lypc.hxs.exception.BusinessException;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;

public class FileUtil {


    public static String generateFilePath(String fileName) {
        //根据日期生成路径   2022/1/15/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String datePath = sdf.format(new Date());

        //uuid作为文件名
        String uuid = StrUtil.uuid().replaceAll("-", "");


        //后缀和文件后缀一致
        int index = fileName.lastIndexOf(".");
        String fileType = fileName.substring(index);

        return datePath + uuid + fileType;
    }

    @SneakyThrows
    public static File MultipartFile2File(MultipartFile multipartFile, String fileName) {
        File file = new File(fileName);
        OutputStream outputStream = new FileOutputStream(file);
        for (byte fileByte : multipartFile.getBytes()) {
            outputStream.write(fileByte);
        }
        return file;
    }


    public static void judgeImageValid(MultipartFile multipartFile, String fileName) {

        //通过后缀名判断是否是安全的
        int index = fileName.lastIndexOf(".");
        String suffix = fileName.substring(index + 1);
        if (index == -1 || StrUtil.isEmpty(suffix)) {
            throw BusinessException.withErrorCode(StatusCode.CLIENT.FILE_TYPE_ERROR.getCode(), StatusCode.CLIENT.FILE_TYPE_ERROR.getMessage());
        }
        HashSet<String> allowSuffix = new HashSet<>(Arrays.asList("jpg", "jpeg", "png", "gif"));
        if (!allowSuffix.contains(suffix.toLowerCase())) {
            throw BusinessException.withErrorCode(StatusCode.CLIENT.FILE_TYPE_ERROR.getCode(), StatusCode.CLIENT.FILE_TYPE_ERROR.getMessage());
        }

        //通过文件流头部判断文件  基本是安全的
        File file = MultipartFile2File(multipartFile, fileName);
        String type = cn.hutool.core.io.FileUtil.getType(file);
        if(!allowSuffix.contains(type)){
            throw BusinessException.withErrorCode(StatusCode.CLIENT.FILE_TYPE_ERROR.getCode(), StatusCode.CLIENT.FILE_TYPE_ERROR.getMessage());
        }
    }


}
