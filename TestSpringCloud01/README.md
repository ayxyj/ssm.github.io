# springcloud-netflix  五大组件

#### 介绍
springcloud-netflix - 20210803
- Eureka
- Ribbon
- Feign
- Hystrix
- Zuul

- Config

#### 软件架构

![module](https://images.gitee.com/uploads/images/2021/0803/231354_29f1ad5b_1538303.png "1.png")


#### 使用说明

1. 主要就是注意版本问题
    - 代码中版本：
```xml
  <!--springCloud的依赖-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Greenwich.SR1</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--SpringBoot-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.1.4.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
```
```xml
     <!--
        依赖的版本冲突问题，推荐：
            1、SpringCloud： Hoxton.SR8
            2、SpringBoot：2.3.3.RELEASE

        以下版本全为：2.2.5.RELEASE
            3、服务者：pring-cloud-starter-netflix-eureka-client
            4、Eureka：spring-cloud-starter-netflix-eureka-server
            5、消费者+Ribbon：spring-cloud-starter-netflix-eureka-client
                spring-cloud-starter-netflix-ribbon
            6、Feign:spring-cloud-starter-openfeign
            7、Hystrix：spring-cloud-starter-netflix-hystrix
	            spring-cloud-starter-netflix-hystrix-dashboard
            8、zuul：spring-cloud-starter-netflix-zuul
        版本推荐可以看spring cloud官网里面全都有
        -->
```
2. SpringBoot基础
