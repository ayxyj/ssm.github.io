package cn.edu.zzu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("cn.edu.zzu.mapper")//扫描mapper包下的到spring
//@Import(JdbcConfig.class)  //配置类，当使用纯注解方式时候，定义数据源，注入到容器
//@PropertySource("classpath:jdbc.properties") //加载资源供@Value注入到属性中去
public class SpringConfiguration {
}
