package cn.edu.zzu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/world")
    public String sayHi(){
        return "hello world!";
    }
}
