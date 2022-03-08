package cn.edu.zzu.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.util.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * 手动使用druid连接池
 * 并配置数据源，监控台
 *
 * @Configuration
 * @ConditionalOnProperty("spring.datasource.type")
 */

public class DruidConfiguration {
    /**
     * Spring Boot配置注解执行器没有配置
     * 配置注解执行器配置完成后，当执行类中已经定义了对象和该对象的字段后，在配置文件中对该类赋值时，便会非常方便的弹出提示信息。
     *
     * @ConfigurationProperties(prefix = "spring.datasource")
     * 使用上面注解而不用下面的@Value更加方便
     * @Value("spring.datasource.url") private String url ;
     */


    @Bean
    //方法一：会绑定application.yaml祝所有spring.datasource开头的配置绑定到datasource
    //@ConfigurationProperties(prefix = "spring.datasource")
    //方法二： druid的构建者模式
    public DataSource getDataSource(DataSourceProperties datasourceProperties) {
        //根据配置动态构建一个datasource
        return datasourceProperties.initializeDataSourceBuilder().build();
        //  DruidDataSource druidDataSource = new DruidDataSource();
        //  return druidDataSource;
    }

    /**
     * druid 监控台
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 添加IP白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // 添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
        //servletRegistrationBean.addInitParameter("deny", "127.0.0.1");
        // 添加控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        // 是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 配置服务过滤器
     *
     * @return 返回过滤器配置对象
     */
    @Bean
    public FilterRegistrationBean statFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 忽略过滤格式
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,");
        return filterRegistrationBean;
    }


    /**
     * 去除监控页面底部的广告
     *
     *         <dependency>
     *             <groupId>com.alibaba</groupId>
     *             <artifactId>druid-spring-boot-starter</artifactId>
     *             <version>1.1.22</version>
     *         </dependency>
     */
     @SuppressWarnings({ "rawtypes", "unchecked" })
     @Bean
     @ConditionalOnProperty(name = "spring.datasource.druid.statViewServlet.enabled", havingValue = "true")
     public FilterRegistrationBean removeDruidFilterRegistrationBean(DruidStatProperties properties)
     {
     // 获取web监控页面的参数
     DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
     // 提取common.js的配置路径
     String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
     String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
     final String filePath = "support/http/resources/js/common.js";
     // 创建filter进行过滤
     Filter filter = new Filter()
     {
     @Override public void init(javax.servlet.FilterConfig filterConfig) throws ServletException
     {
     }
     @Override public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
     throws IOException, ServletException
     {
     chain.doFilter(request, response);
     // 重置缓冲区，响应头不会被重置
     response.resetBuffer();
     // 获取common.js
     String text = Utils.readFromResource(filePath);
     // 正则替换banner, 除去底部的广告信息
     text = text.replaceAll("<a.*?banner\"></a><br/>", "");
     text = text.replaceAll("powered.*?shrek.wang</a>", "");
     response.getWriter().write(text);
     }
     @Override public void destroy()
     {
     }
     };
     FilterRegistrationBean registrationBean = new FilterRegistrationBean();
     registrationBean.setFilter(filter);
     registrationBean.addUrlPatterns(commonJsPattern);
     return registrationBean;
     }
}