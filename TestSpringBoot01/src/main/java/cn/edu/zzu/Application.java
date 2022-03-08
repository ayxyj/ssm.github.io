package cn.edu.zzu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //标记springBoot的启动类
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class , args);
        Logger logger = LoggerFactory.getLogger(Application.class);
        logger.trace("跟踪");
        logger.debug("调试");
        logger.info("信息");
        logger.warn("警告");
        logger.error("错误");
    }
}
