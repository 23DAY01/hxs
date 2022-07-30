package com.lypc.hxs.controller;

import cn.hutool.core.util.StrUtil;
import com.lypc.hxs.constant.WebConst;
import com.lypc.hxs.pojo.domain.User;
import com.lypc.hxs.service.UserService;
import com.lypc.hxs.utils.AuthUtil;
import com.lypc.hxs.utils.ResponseAPI;
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
 * 用户表 前端控制器
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("用户登录")
    @GetMapping("/login")
    public ResponseAPI<?> Login(HttpServletRequest request,
                                HttpServletResponse response,
                                @ApiParam(name = "userName", value = "用户账户", required = true)
                                @RequestParam(name = "userName")
                                        String userName,
                                @ApiParam(name = "userPassword", value = "用户密码", required = true)
                                @RequestParam(name = "userPassword")
                                        String userPassword,
                                @ApiParam(name = "remember_me", value = "remember_me")
                                @RequestParam(name = "remember_me", required = false)
                                        String remember_me) {

        User user = userService.login(userName, userPassword);
        //登录成功后添加状态
        request.getSession().setAttribute(WebConst.AUTHENTICATION.USER_SESSION, user);
        if (StrUtil.isNotBlank(remember_me)) {
            AuthUtil.setUserCookie(response, user.getUserId());
        }

        return ResponseAPI.success();
    }


    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        session.removeAttribute(WebConst.AUTHENTICATION.USER_SESSION);
        Cookie cookie = new Cookie(WebConst.AUTHENTICATION.USER_COOKIE, "");
        cookie.setValue(null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }


}
