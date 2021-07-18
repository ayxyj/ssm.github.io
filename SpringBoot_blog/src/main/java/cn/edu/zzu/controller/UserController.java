package cn.edu.zzu.controller;


import cn.edu.zzu.common.lang.Result;
import cn.edu.zzu.entity.User;
import cn.edu.zzu.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzu
 * @since 2021-07-05
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequiresAuthentication
    @GetMapping("/index")
    public Result index() {
        User user = userService.getById(1L);
        return Result.success(user);
    }

    @PostMapping("/save")
    public Result save(@Validated  @RequestBody User user) {
        boolean save = userService.save(user);
        return Result.success(user);
    }
}
