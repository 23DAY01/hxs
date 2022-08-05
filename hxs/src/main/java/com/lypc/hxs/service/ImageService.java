package com.lypc.hxs.service;

import com.lypc.hxs.pojo.domain.Image;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
public interface ImageService extends IService<Image> {

    String uploadImage(MultipartFile file,Integer userId);

    void delImage(Integer id);

}
