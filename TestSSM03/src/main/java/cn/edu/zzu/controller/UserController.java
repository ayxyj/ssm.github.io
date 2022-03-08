package cn.edu.zzu.controller;

import cn.edu.zzu.domain.User;
import cn.edu.zzu.queryVo.UserQueryVo;
import cn.edu.zzu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/hello")
    @ResponseBody
    public String test() {
        return "<h1>Hello MVC</h1>";
    }

    @Autowired
    IUserService iUserService;

    /**
     * 跳转首页
     *
     * @return
     */
    @GetMapping("/goMain")
    public String goMain() {
        //return "redirect:/index.jsp";
        //return "forward:/index.jsp";
        return "/index.jsp";
    }

    /**
     * 跳转添加页面
     *
     * @return
     */
    @GetMapping("/goAdd")
    public String goAdd() {
        return "user/adduser";
    }

    /**
     * 跳转更新页面
     *
     * @return
     */
    @GetMapping("/goUpdate/{id}")
    public String goUpdate(@PathVariable("id") Integer id, Model model) {
        User userById = iUserService.findUserById(id);
        model.addAttribute("user", userById);
        return "user/updateuser";
    }

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @GetMapping("/goLogin")
    public String goLogin() {
        return "login";
    }

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<User> all = iUserService.findAll();
        model.addAttribute("list", all);
        return "user/alluser";
    }

    @PostMapping("/addUser")
    public String addUser(User user, Model model) {
        int i = iUserService.insertUser(user);
        if (i == 0) {
            model.addAttribute("msg", "用户添加失败");
            throw new RuntimeException("添加失败");
        }
        return "redirect:/user/findAll";
    }

    @PostMapping("/updateUser")
    public String updateUser(User user, Model model) {
        int i = iUserService.updateUser(user);
        if (i == 0) {
            model.addAttribute("msg", "用户更新失败");
            throw new RuntimeException("更新失败");
        }
        return "redirect:/user/findAll";
    }

    @PostMapping("/findUserByName")
    public String findUserByName(String username, Model model) {
        List<User> userByName = iUserService.findUserByName(username);
        model.addAttribute("list", userByName);
        if (userByName.size() == 0) {
            model.addAttribute("error", "查询用户不存在");
        }
        return "user/alluser";
    }

    /**
     * 删除
     */
    @GetMapping("/deleteUserById")
    public String deleteUserByid(Integer id, Model model) {
        int i = iUserService.deleteUserById(id);
        if (i == 0) {
            model.addAttribute("msg", "删除失败");
            throw new RuntimeException("删除异常");
        }
        return "redirect:/user/findAll";
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public String login(User user, HttpSession httpSession, Model model) {
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setUser(user);
        User userQuery = iUserService.findUserByQueryVo(userQueryVo);

        httpSession.setAttribute("userLoginInfo", userQuery);

        ArrayList<User> users = new ArrayList<>();
        users.add(userQuery);

        model.addAttribute("list", users);

        return "redirect:/user/findAll";
    }

    /**
     * 退出
     */
    @GetMapping("/loginOut")
    public String loginOut(HttpSession httpSession) {
        Object userLoginInfo = httpSession.getAttribute("userLoginInfo");
        if (userLoginInfo != null) {
            httpSession.removeAttribute("userLoginInfo");
        }
        return "redirect:/index.jsp";
    }

}

