package cn.edu.zzu.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * 配置Swagger配置类，并开启
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //配置Swagger的docket的bean实例
    @Bean
    public Docket docket(Environment environment){

        //设置要显示的Swagger环境
        Profiles of = Profiles.of("dev", "test");
        //通过environment.acceptsProfiles判断是否处于自己设定的环境当中
        boolean flag = environment.acceptsProfiles(of);

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("哈哈哈")
                .apiInfo(apiInfo())
                .enable(flag)//enable是否启动Swagger，如果为false，则swagger不能在浏览器中访问
                .select()
                //配置Swagger的要扫描的包，RequestHandlerSelectors，配置要扫描接口的方式
                .apis(RequestHandlerSelectors.basePackage("cn.edu.zzu.controller"))
                .paths(PathSelectors.ant("/user/**"))//过滤请求路径
                .build();
    }

    //如何配置多个分组？配置多个分组只需要配置多个docket即可：
    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("group1");
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("group2");
    }
    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("group3");
    }

    //配置Swagger信息-apiInfo
    private ApiInfo apiInfo(){
        //联系信息
        Contact DEFAULT_CONTACT = new Contact("哈哈Test", "https://zzugo.ayxyj.cn", "87089043@qq.com");

        return new ApiInfo("哈哈Swagger RestFull Api Documentation",
                "哈哈Test Swagger RestFull Api",
                "1.0",
                "https://zzugo.ayxyj.cn",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
