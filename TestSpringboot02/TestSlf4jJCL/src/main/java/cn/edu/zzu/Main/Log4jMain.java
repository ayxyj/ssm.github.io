package cn.edu.zzu.Main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jMain {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Log4jMain.class);
        System.out.println(logger.getClass());
        logger.info("崇尚开源：log4j");
    }
}
