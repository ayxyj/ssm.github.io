package cn.edu.zzu.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;

public class TimeInterceptor implements HandlerInterceptor {
    LocalDateTime begin = null ;
    LocalDateTime end = null ;
    Logger logger = LoggerFactory.getLogger(TimeInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //开始时间
        begin = LocalDateTime.now();
        logger.info("当前请求："+request.getRequestURI()+"开始时间："+begin+"毫秒");
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //结束时间
        end = LocalDateTime.now();

        //获得相差的毫秒数
        Duration time = Duration.between(begin , end);
       logger.info("当前请求："+request.getRequestURI()+"时间差："+time.toMillis()+"毫秒");
    }
}
