package cn.edu.zzu.config;

import cn.edu.zzu.interceptors.TimeInterceptor;
import cn.edu.zzu.servlet.BeanServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    /**
     * 添加视图控制器
     * 立即访问
     * <mvc:view-controller path="/" view-name="index"></>
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/zzu").setViewName("hello");
    }

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TimeInterceptor())
                .addPathPatterns("/**")//添加拦截器 , ；拦截所有请求
                .excludePathPatterns("/pages/**");//排除静态资源

        //添加国际化拦截器
        registry.addInterceptor(new LocaleChangeInterceptor())
                .addPathPatterns("/**");//拦截所有请求
    }

    /**
     * 全局CORS的配置
     * 添加跨域请求映射
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/order/*")//y映射服务器中那些http接口允许跨域请求
                .allowedOrigins("http://localhost:8082")
        .allowedMethods("PUT","DELETE","GET","POST");//配置允许跨域请求的请求方法
    }

    /**
     * 本地国际化
     */
    @Bean
    public LocaleResolver localeResolver(){
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        //设置过期时间
        cookieLocaleResolver.setCookieMaxAge(60);
        cookieLocaleResolver.setCookieName("locale");
        return cookieLocaleResolver;
    }
    @Bean
    public ServletRegistrationBean myServlet(){
        //声明一个servlet注册器bean
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new BeanServlet());
        servletRegistrationBean.setName("beanServlet");
        servletRegistrationBean.addUrlMappings("/beanServlet");
        return servletRegistrationBean;
    }
}
