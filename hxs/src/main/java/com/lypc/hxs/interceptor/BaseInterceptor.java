package com.lypc.hxs.interceptor;

import com.lypc.hxs.constant.StatusCode;
import com.lypc.hxs.constant.WebConst;
import com.lypc.hxs.exception.BusinessException;
import com.lypc.hxs.pojo.domain.Admin;
import com.lypc.hxs.pojo.domain.User;
import com.lypc.hxs.service.AdminService;
import com.lypc.hxs.service.UserService;
import com.lypc.hxs.utils.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class BaseInterceptor implements HandlerInterceptor {


    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();

        //如果需要用户权限
        if (uri.startsWith("/user") && !uri.startsWith("/user/login")) {

            User user = AuthUtil.getLoginUser(request);
            if(user==null){
                Integer userId = AuthUtil.getCookieUserId(request);

                if(userId!=null){
                    user = userService.getById(userId);
                    request.getSession().setAttribute(WebConst.AUTHENTICATION.USER_SESSION,user);
                }else {
                    //如果没有登录 就抛出没有登录异常
                    throw BusinessException.withErrorCode(StatusCode.CLIENT.AUTH_NOLOGIN.getCode(),StatusCode.CLIENT.AUTH_NOLOGIN.getMessage());
                }
            }


        }

        //如果需要管理员权限
        if (uri.startsWith("/admin") && !uri.startsWith("/admin/login")) {
            //    先获取登录的管理员
            Admin admin = AuthUtil.getLoginAdmin(request);

            if (admin == null) {
                Integer adminId = AuthUtil.getCookieAdminId(request);

                //    如果获取到了就ok
                if (adminId != null) {
                    admin = adminService.getById(adminId);
                    //依靠cookie查出来的admin放到session里面去
                    //这边不知道为啥会有这种操作  按理说cookie和session用一个即可
                    request.getSession().setAttribute(WebConst.AUTHENTICATION.ADMIN_SESSION, admin);
                } else {
                    //    如果没有获取到就抛没有权限异常
                    throw BusinessException.withErrorCode(StatusCode.CLIENT.AUTH_PERMISSION_DENIED.getCode(), StatusCode.CLIENT.AUTH_PERMISSION_DENIED.getMessage());
                }
            }

            return true;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


}
