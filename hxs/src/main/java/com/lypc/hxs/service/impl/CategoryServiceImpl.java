package com.lypc.hxs.service.impl;

import com.lypc.hxs.pojo.domain.Category;
import com.lypc.hxs.mapper.CategoryMapper;
import com.lypc.hxs.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章分类 服务实现类
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
