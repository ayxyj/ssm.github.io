package cn.edu.zzu.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:jdbc.properties")
@ComponentScan("cn.edu.zzu")
@Import(JdbcConfig.class)
public class SpringConfiguration {
}
