package cn.edu.zzu.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object userLoginInfo = request.getSession().getAttribute("userLoginInfo");
        //登录页面放行
        if(request.getRequestURI().contains("goLogin")){
            if(userLoginInfo!=null){
                response.sendRedirect("/user/findAll");
            }
            return true;
        }
        //首次登录放行
        if(request.getRequestURI().contains("login")){
            return true;
        }
        if(userLoginInfo !=null){
            return true;
        }
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request,response);
        return false;
    }
}
