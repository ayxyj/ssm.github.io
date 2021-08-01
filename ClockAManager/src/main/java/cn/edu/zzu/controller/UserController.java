package cn.edu.zzu.controller;

import cn.edu.zzu.domain.User;
import cn.edu.zzu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService iUserService;

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

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<User> all = iUserService.findAll();
        if (all.size() == 0) {
            model.addAttribute("userSize", "未查询到用户");
        }
        model.addAttribute("list", all);
        return "user/allUser";
    }

    @PostMapping("/addUser")
    public String addUser(User user, Model model) {
        System.out.println("封装前：" + user);
        User user1 = userOtherProperties(user);
        System.out.println("封装后：" + user1);
        int i = iUserService.insertUser(user1);
        if (i == 0) {
            model.addAttribute("msg", "用户添加失败");
            throw new RuntimeException("添加失败");
        }
        return "redirect:/user/findAll";
    }

    @PostMapping("/updateUser")
    public String updateUser(User user, Model model) {
        User user1 = userOtherProperties(user);
        int i = iUserService.updateUser(user1);
        if (i == 0) {
            model.addAttribute("msg", "用户更新失败");
            throw new RuntimeException("更新失败");
        }
        return "redirect:/user/findAll";
    }

    @PostMapping("/findUserByName")
    public String findUserByName(String uid, Model model) {
        User userByUid = iUserService.findUserByUid(uid);
        if (userByUid == null) {
            model.addAttribute("error", "学号不存在");
            List<User> all = iUserService.findAll();
            model.addAttribute("list", all);
        } else {
            ArrayList<User> users = new ArrayList<>();
            users.add(userByUid);
            model.addAttribute("list", users);
        }
        return "user/allUser";
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
     * 其他属性
     *
     * @param user
     * @return
     */
    public User userOtherProperties(User user) {
        user.setMyvs_1("否");
        user.setMyvs_2("否");
        user.setMyvs_3("否");
        user.setMyvs_4("否");
        user.setMyvs_5("否");
        user.setMyvs_6("否");
        user.setMyvs_7("否");
        user.setMyvs_8("否");
        user.setMyvs_9("否");
        user.setMyvs_10("否");
        user.setMyvs_11("否");
        user.setMyvs_12("否");
        user.setMyvs_13a("41");
        user.setMyvs_13b("4101");
        user.setMyvs_13c("郑州大学北校区");
        user.setMyvs_14("否");
        user.setRecord("0");
        user.setSend("0");
        return user;
    }
}
