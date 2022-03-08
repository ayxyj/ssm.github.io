package cn.edu.zzu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages ={"cn.edu.zzu"})
public class ApplicationConsumer_Feign {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationConsumer_Feign.class ,args);
    }
}
