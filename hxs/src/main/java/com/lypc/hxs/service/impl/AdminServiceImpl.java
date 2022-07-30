package com.lypc.hxs.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lypc.hxs.constant.StatusCode;
import com.lypc.hxs.exception.BusinessException;
import com.lypc.hxs.pojo.domain.Admin;
import com.lypc.hxs.mapper.AdminMapper;
import com.lypc.hxs.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import net.bytebuddy.build.RepeatedAnnotationPlugin;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-27
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {


    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String adminName, String adminPassword) {

        //密码加密后与数据库比对
        String pwd = SecureUtil.md5(adminPassword);

        Admin admin = getAdminByInfo(adminName, pwd);

        //如果查不到admin 则是账号或密码错误
        if (admin == null) {
            //后续添加失败延迟功能

            throw BusinessException.withErrorCode(StatusCode.CLIENT.AUTH_UorP_ERROR.getCode(), StatusCode.CLIENT.AUTH_UorP_ERROR.getMessage());
        }

        return admin;
    }

    @Override
    public Admin getAdminByInfo(String adminName, String AdminPassword) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(Admin::getAdminName, adminName).eq(Admin::getAdminPasswd, AdminPassword);

        return adminMapper.selectOne(wrapper);
    }

}
