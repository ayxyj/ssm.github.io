package cn.edu.zzu.controller;

import cn.edu.zzu.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制器类
 */
@Controller
@RequestMapping(value="Account")
public class AccountController {

    @RequestMapping( path = "/hello")
    public String  sayHello(){
        System.out.println("hello！");
        return "success";
    }
    @RequestMapping(path="/parameter")
    public String parameter(String username ,Double money){
        System.out.println(username + " : " + money);
        return "parameter";
    }

    @RequestMapping(value="/saveAccount")
    public String saveAccount(Account account){
        System.out.println(account);
        return "account";
    }
}
