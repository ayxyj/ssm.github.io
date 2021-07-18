package cn.edu.zzu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    //@RequestParam("username") : username提交的域的名称 .  提交的域名称和处理方法的参数名不一致
    @RequestMapping("/hello1")
    public String hello(@RequestParam("username") String name , Model mode ){
        System.out.println(name);
        mode.addAttribute("msg" , name);
        return "hello";
    }

    @RequestMapping("/hello2")
    public String hello(@RequestParam("username") String name, ModelMap model){
        //封装要显示到视图中的数据
        //相当于req.setAttribute("name",name);
        model.addAttribute("name",name);
        System.out.println(name);
        return "hello";
    }
}
