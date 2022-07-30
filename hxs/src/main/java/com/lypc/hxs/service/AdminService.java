package com.lypc.hxs.service;

import com.lypc.hxs.pojo.domain.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-27
 */
public interface AdminService extends IService<Admin> {

    public Admin login(String adminName, String adminPassword);

    public Admin getAdminByInfo(String adminName, String AdminPassword);

}
