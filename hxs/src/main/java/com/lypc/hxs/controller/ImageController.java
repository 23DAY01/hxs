package com.lypc.hxs.controller;

import com.lypc.hxs.constant.StatusCode;
import com.lypc.hxs.pojo.domain.Image;
import com.lypc.hxs.service.ImageService;
import com.lypc.hxs.utils.AuthUtil;
import com.lypc.hxs.utils.Fileutil;
import com.lypc.hxs.utils.QiniuUtil;
import com.lypc.hxs.utils.ResponseAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-28
 */
@Api(tags = "图片controller")
@RestController
@RequestMapping("/image")
public class ImageController {


    @Autowired
    private ImageService imageService;

    @Autowired
    private QiniuUtil qiniuUtil;

    @ApiOperation(value = "上传图片", notes = "文件上传即可")
    @PostMapping("/uploadImg")
    public ResponseAPI<?> uploadImage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                      @ApiParam(name = "img", value = "图片", required = true)
                                      @RequestParam(name = "img")
                                              MultipartFile img,HttpServletRequest request) {

        //通过文件流头部判断文件  基本是安全的
        //String type = FileUtil.getType((File) img);

        //获取文件路径
        String filename = img.getOriginalFilename();
        //判断图片安全性
        Fileutil.judgeImageValid(img, filename);
        String filePath = Fileutil.generateFilePath(filename);

        //上传图片到七牛云
        String key = qiniuUtil.uploadImage(img, filePath);

        //获取当前用户id
        Integer userId = AuthUtil.getCurrentUserId(request);

        //封装image类到mysql
        Image image = new Image();
        String url=qiniuUtil.DOMAIN_NAME + key;
        image.setUserId(userId);
        image.setImageUrl(url);

        imageService.save(image);

        return ResponseAPI.success(url, StatusCode.SUCCESS.OK.getCode());
    }


    @ApiOperation("删除图片")
    @GetMapping("/delImg")
    public ResponseAPI<?> delImage(
            @ApiParam(name = "id", value = "图片的key",required = true)
            @RequestParam(name = "id")
                    Integer id) {

        Image image = imageService.getById(id);

        //七牛云del
        qiniuUtil.delImage(qiniuUtil.Url2Key(image.getImageUrl()));

        //数据库del
        imageService.removeById(id);

        return ResponseAPI.success(StatusCode.SUCCESS.OK.getCode());

    }

}
