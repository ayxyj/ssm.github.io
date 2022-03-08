package cn.edu.zzu.controller;

import cn.edu.zzu.domain.Admin;
import cn.edu.zzu.domain.User;
import cn.edu.zzu.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    IAdminService iAdminService ;
    /**
     * goLogin
     */
    @GetMapping("/goLogin")
    public  String goLogin(){
        return "login";
    }
    /**
     * goMain
     */
    @GetMapping("/goMain")
    public  String goMain(){
        return "common/main";
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public String login(Admin admin , Model model , HttpSession httpSession){
        String password = DigestUtils.md5DigestAsHex((admin.getPassword()).getBytes());

        Admin adminByName = iAdminService.findAdminByName(admin.getUsername());
        if(adminByName.getUsername().equals(admin.getUsername()) && adminByName.getPassword().equals(password)){
            if(adminByName.getStatus()==0){
                model.addAttribute("status","用户禁用");
                return "login";
            }
            httpSession.setAttribute("LoginUserInfo" ,adminByName);
            return "common/main";
        }else{
            model.addAttribute("pwdError" , "密码错误");
            return "login";
        }
    }
    /**
     * 退出
     */
    @GetMapping("/loginOut")
        public String loginOut(HttpSession httpSession){
        Object loginUserInfo = httpSession.getAttribute("LoginUserInfo");
        if (loginUserInfo == null) {
            throw new RuntimeException("未找到属性");
        }
        httpSession.removeAttribute("LoginUserInfo");
        return "login";
    }
}
