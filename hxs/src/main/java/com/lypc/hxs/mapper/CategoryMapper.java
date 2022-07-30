package com.lypc.hxs.mapper;

import com.lypc.hxs.pojo.domain.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章分类 Mapper 接口
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
