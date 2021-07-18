package cn.edu.zzu.testspringboot.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserControlller {
    @RequestMapping("/hello")
    public String getUsername(Model model){
        model.addAttribute("username" , "xushu");
        return  "index";
    }

    @RequestMapping("/list")
    public String getList(Model model){
        List<String> usernames = Arrays.asList("zz01", "zz02", "zz03");
        model.addAttribute("usernames" , usernames);
        return  "list";
    }
}
