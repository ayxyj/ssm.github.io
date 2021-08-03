package cn.edu.zzu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient//默认服务发现开启，不需要手动开启
public class DeptProvider_8002{
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider_8002.class ,args);
    }
}
