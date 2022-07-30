package com.lypc.hxs.mapper;

import com.lypc.hxs.pojo.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
