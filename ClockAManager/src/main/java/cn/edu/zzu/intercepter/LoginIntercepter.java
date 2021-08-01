package cn.edu.zzu.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUserInfo = request.getSession().getAttribute("LoginUserInfo");

        if (request.getRequestURI().contains("goLogin")){
            if (loginUserInfo!=null){
                request.setAttribute("msg" , "已经登录了!");
                response.sendRedirect("/admin/goMain");
            }
            return true;
        }

        if(request.getRequestURI().contains("login")){
            return true;
        }

        if(loginUserInfo !=null){
            return true;
        }
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request,response);
        return false;
    }
}
