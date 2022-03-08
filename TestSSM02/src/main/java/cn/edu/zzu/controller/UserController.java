package cn.edu.zzu.controller;

import cn.edu.zzu.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController {
    @RequestMapping("/hello")
    @ResponseBody
    public String test() {
        return "<h1>Hello 哈哈！</h1>";
    }

    @RequestMapping("/a1")
    @ResponseBody
    public void a1(String name, HttpServletResponse response) throws IOException {
        System.out.println(name);
        if ("zzu".equals(name)) {
            response.getWriter().print("true");
        } else {
            response.getWriter().print("false");
        }
    }

    @RequestMapping("/a2")
    @ResponseBody
    public List<User> a2(String name, HttpServletResponse response) throws IOException {
        ArrayList<User> userArrayList = new ArrayList<>();
        userArrayList.add(new User("zzu", "123456"));
        userArrayList.add(new User("妹子", "123456"));
        return userArrayList;
    }

    @RequestMapping("/a3")
    @ResponseBody
    public String a3(String name, String pwd) {
        String msg = null;
        if (name != null) {
            if ("admin".equals(name)) {
                msg = "ok";
            } else {
                msg = "用户名错误";
            }
        }
        if (pwd != null) {
            if ("123456".equals(pwd)) {
                msg = "ok";
            } else {
                msg = "密码错误";
            }
        }
        return msg;
    }
}
