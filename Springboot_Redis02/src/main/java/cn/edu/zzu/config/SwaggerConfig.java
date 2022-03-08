package cn.edu.zzu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("groupZZU");
    }

    /**
     * 配置Docket信息
     */
    private ApiInfo apiInfo() {

        new Contact("名字","https://zzugo.ayxyj.cn","87089043@qq.com");
        return new ApiInfo("Swagger Api",
                "Test Swagger RestFull Api",
                "1.0",
                "https://zzugo.ayxyj.cn",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
