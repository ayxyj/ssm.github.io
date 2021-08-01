package cn.edu.zzu.controller;

import cn.edu.zzu.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        logger.trace("跟踪");
        logger.debug("调试");
        logger.info("信息");
        logger.warn("警告");
        logger.error("错误");
        return "<h1 style='color:red; font:blod;'>hello springboot!</h1>";
    }

    @Autowired
    Person person;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return person.toString();
    }
}
