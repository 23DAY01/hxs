package com.lypc.hxs.controller;

import com.lypc.hxs.constant.StatusCode;
import com.lypc.hxs.pojo.domain.Image;
import com.lypc.hxs.service.ImageService;
import com.lypc.hxs.utils.AuthUtil;
import com.lypc.hxs.utils.FileUtil;
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
@RequestMapping("/user/image")
public class ImageController {


    @Autowired
    private ImageService imageService;


    @ApiOperation(value = "上传图片", notes = "文件上传即可")
    @PostMapping("/uploadImg")
    public ResponseAPI<?> uploadImage(
            @ApiParam(name = "img", value = "图片", required = true)
            @RequestParam(name = "img")
                    MultipartFile img, HttpServletRequest request) {

        //获取当前用户id
        Integer userId = AuthUtil.getCurrentUserId(request);

        //上传图片
        String url = imageService.uploadImage(img, userId);

        return ResponseAPI.success(url, StatusCode.SUCCESS.OK.getCode());
    }


    @ApiOperation("删除图片")
    @GetMapping("/delImg")
    public ResponseAPI<?> delImage(
            @ApiParam(name = "id", value = "图片的key", required = true)
            @RequestParam(name = "id")
                    Integer id) {
        imageService.delImage(id);
        return ResponseAPI.success(StatusCode.SUCCESS.OK.getCode());
    }

}
