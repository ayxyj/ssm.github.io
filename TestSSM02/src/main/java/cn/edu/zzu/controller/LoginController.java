package cn.edu.zzu.controller;

import cn.edu.zzu.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {
    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    /**
     * 跳转首页
     * @return
     */
    @RequestMapping("/goMain")
    public String toMain(){
        return "main";
    }

    /**
     * 验证登录
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login(User user , HttpSession session){
        System.out.println(user);
        session.setAttribute("userLoginInfo" , user);
        return "main";
    }

    @RequestMapping("/goOut")
    public String Out(HttpSession session){
        session.removeAttribute("userLoginInfo");
        return "login";
    }
}
