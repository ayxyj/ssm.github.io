package cn.edu.zzu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("scope")
@SessionAttributes(value = "msg") //把msg存储到session域
public class ScopeController {

    @RequestMapping("/testSessionAttribute")
    public String testSessionAttribute(Model model){
        model.addAttribute("msg" , "zzu");
        return "scope";
    }

    @RequestMapping("/getSessionAttribute")
    public String getSessionAttribute(ModelMap map){
        String  msg =(String) map.get("msg");
        System.out.println(msg);
        return "scope";
    }

    @RequestMapping("delSessionAttribute")
    public String delSessionAttribute(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "scope";
    }
}
