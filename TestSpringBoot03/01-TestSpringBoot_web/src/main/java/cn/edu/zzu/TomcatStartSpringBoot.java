package cn.edu.zzu;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 当tomcat启动时就会调用configure方法，从而在springboot启动类的基础上启动springboot
 */
public class TomcatStartSpringBoot extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return  builder.sources(Application.class);
    }
}
