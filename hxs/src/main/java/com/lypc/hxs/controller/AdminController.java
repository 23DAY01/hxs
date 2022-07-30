package com.lypc.hxs.controller;

import cn.hutool.core.util.StrUtil;
import com.lypc.hxs.constant.WebConst;
import com.lypc.hxs.exception.BusinessException;
import com.lypc.hxs.pojo.domain.Admin;
import com.lypc.hxs.service.AdminService;
import com.lypc.hxs.utils.AuthUtil;
import com.lypc.hxs.utils.ResponseAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
@Api(tags = "管理员操作")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation("管理员登录")
    @GetMapping("/login")
    public ResponseAPI<?> Login(HttpServletRequest request,
                                HttpServletResponse response,
                                @ApiParam(name = "adminName", value = "管理员账户", required = true)
                                @RequestParam(name = "adminName")
                                        String adminName,
                                @ApiParam(name = "adminPassword", value = "管理员密码", required = true)
                                @RequestParam(name = "adminPassword")
                                        String adminPassword,
                                @ApiParam(name = "remember_me", value = "remember_me")
                                @RequestParam(name = "remember_me", required = false)
                                        String remember_me) {

        Admin admin = adminService.login(adminName, adminPassword);
        //登录成功后添加状态
        request.getSession().setAttribute(WebConst.AUTHENTICATION.ADMIN_SESSION, admin);
        if (StrUtil.isNotBlank(remember_me)) {
            AuthUtil.setAdminCookie(response, admin.getAdminId());
        }

        return ResponseAPI.success();
    }


    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        session.removeAttribute(WebConst.AUTHENTICATION.ADMIN_SESSION);
        Cookie cookie = new Cookie(WebConst.AUTHENTICATION.ADMIN_COOKIE, "");
        cookie.setValue(null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
