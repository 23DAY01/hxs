package com.lypc.hxs.service.impl;

import com.lypc.hxs.pojo.domain.Image;
import com.lypc.hxs.mapper.ImageMapper;
import com.lypc.hxs.service.ImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lypc.hxs.utils.AuthUtil;
import com.lypc.hxs.utils.FileUtil;
import com.lypc.hxs.utils.QiniuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {

    @Autowired
    private QiniuUtil qiniuUtil;

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public String uploadImage(MultipartFile img,Integer userId) {
        //获取文件路径
        String filename = img.getOriginalFilename();
        //判断图片安全性
        FileUtil.judgeImageValid(img, filename);
        String filePath = FileUtil.generateFilePath(filename);

        //上传图片到七牛云
        String key = qiniuUtil.uploadImage(img, filePath);

        //封装image类到mysql
        Image image = new Image();
        String url = qiniuUtil.DOMAIN_NAME + key;
        image.setUserId(userId);
        image.setImageUrl(url);
        save(image);

        return url;
    }

    @Override
    public void delImage(Integer id) {
        Image image = imageMapper.selectById(id);

        //七牛云del
        qiniuUtil.delImage(qiniuUtil.Url2Key(image.getImageUrl()));

        //数据库del
        removeById(id);
    }
}
