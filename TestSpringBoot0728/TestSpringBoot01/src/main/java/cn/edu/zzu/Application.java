package cn.edu.zzu;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //SpringApplication.run(Application.class);

        /*
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
        */

        SpringApplication springApplication = new SpringApplication(Application.class);
        Properties properties = new Properties();
        InputStream resourceAsStream = Application.class.getClassLoader().getResourceAsStream("application-prod.yml");
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.setDefaultProperties(properties);
        springApplication.run(args);
    }
}
