package com.lypc.hxs.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import com.lypc.hxs.constant.WebConst;
import com.lypc.hxs.pojo.domain.Admin;
import com.lypc.hxs.pojo.domain.User;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthUtil {

    public static Admin getLoginAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session == null) {
            return null;
        }
        return (Admin) session.getAttribute(WebConst.AUTHENTICATION.ADMIN_SESSION);
    }

    public static User getLoginUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session==null){
            return null;
        }
        return (User)session.getAttribute(WebConst.AUTHENTICATION.USER_SESSION);
    }

    public static Integer getCookieAdminId(HttpServletRequest request) {
        if (request != null) {
            Cookie cookie = getCookie(WebConst.AUTHENTICATION.ADMIN_COOKIE, request);
            if (cookie != null && cookie.getValue() != null) {
                //解密取出cookie
                String adminId = SecUtil.deAes(cookie.getValue(), Mode.CBC, Padding.ZeroPadding);
                //确保adminId是一个int
                return StrUtil.isNotBlank(adminId) && StrUtil.isNumeric(adminId) ? Integer.valueOf(adminId) : null;
            }
        }
        return null;
    }

    public static Integer getCookieUserId(HttpServletRequest request) {
        if (request != null) {
            Cookie cookie = getCookie(WebConst.AUTHENTICATION.USER_COOKIE, request);
            if (cookie != null && cookie.getValue() != null) {
                //解密取出cookie
                String userId = SecUtil.deAes(cookie.getValue(), Mode.CBC, Padding.ZeroPadding);
                //确保userId是一个int
                return StrUtil.isNotBlank(userId) && StrUtil.isNumeric(userId) ? Integer.valueOf(userId) : null;
            }
        }
        return null;
    }

    public static Integer getCurrentUserId(HttpServletRequest request){
        return getCookieUserId(request);
    }

    public static Cookie getCookie(String s, HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(s)) {
                return cookie;
            }
        }
        return null;
    }

    public static void setUserCookie(HttpServletResponse response, Integer userId) {
        //对userId加密
        String s = SecUtil.enAes(userId.toString(), Mode.CBC, Padding.ZeroPadding);
        //生成一个cookie
        Cookie cookie = new Cookie(WebConst.AUTHENTICATION.USER_COOKIE, s);
        cookie.setMaxAge(-1);
        cookie.setSecure(false);
        cookie.setPath("/");
        //添加到cookies里面
        response.addCookie(cookie);
    }

    public static void setAdminCookie(HttpServletResponse response, Integer adminId) {
        String s = SecUtil.enAes(adminId.toString(), Mode.CBC, Padding.ZeroPadding);
        Cookie cookie = new Cookie(WebConst.AUTHENTICATION.ADMIN_COOKIE, s);
        cookie.setMaxAge(-1);
        cookie.setSecure(false);
        cookie.setPath("/");
        response.addCookie(cookie);
    }


}
