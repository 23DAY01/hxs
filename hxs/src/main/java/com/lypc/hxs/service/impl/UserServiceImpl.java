package com.lypc.hxs.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lypc.hxs.constant.StatusCode;
import com.lypc.hxs.exception.BusinessException;
import com.lypc.hxs.mapper.AdminMapper;
import com.lypc.hxs.pojo.domain.Admin;
import com.lypc.hxs.pojo.domain.User;
import com.lypc.hxs.mapper.UserMapper;
import com.lypc.hxs.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String userName, String userPassword) {

        //密码加密后与数据库比对
        String pwd = SecureUtil.md5(userPassword);

        User user = getUserByInfo(userName, pwd);

        //如果查不到admin 则是账号或密码错误
        if (user == null) {
            //后续添加失败延迟功能

            throw BusinessException.withErrorCode(StatusCode.CLIENT.AUTH_UorP_ERROR.getCode(), StatusCode.CLIENT.AUTH_UorP_ERROR.getMessage());
        }

        return user;
    }

    @Override
    public User getUserByInfo(String userName, String userPassword) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(User::getUsername, userName).eq(User::getPassword, userPassword);

        return userMapper.selectOne(wrapper);
    }

}
