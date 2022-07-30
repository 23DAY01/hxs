package com.lypc.hxs.service;

import com.lypc.hxs.pojo.domain.Admin;
import com.lypc.hxs.pojo.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
public interface UserService extends IService<User> {

    public User login(String userName, String userPassword);

    public User getUserByInfo(String userName, String userPassword);
}
