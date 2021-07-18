package cn.edu.zzu.controller;

import cn.edu.zzu.entity.Result;
import cn.edu.zzu.entity.User;
import cn.edu.zzu.service.UserService;
import cn.edu.zzu.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MessageSource messageSource;//国家化查询

    //rest /user/1
    @GetMapping("{id}")
    public Result getUser(@PathVariable Integer id){
        //LocaleContextHolder 就是一个Locale持有器，springmvc底层会自动讲LocaleResovler中语言设置进去
        String message = messageSource.getMessage("user.query.success", null, LocaleContextHolder.getLocale());
        User user = UserService.getUserById(id);
        return new Result(200 , message , user);
    }

    //add  /user/add
    @PostMapping("/add")
    public Result addUser(@RequestBody  User user){
        userService.add(user);
        return new Result(200 , "添加成功");
    }

    //update /user/1
    @PutMapping("/update/{id}")
    public Result editUser(@RequestBody User user) {
        userService.updateUser(user);
        return new Result(200 ,"修改成功");
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin //配置跨域请求
    public Result deleteUser(@PathVariable String id ){
        userService.deleteUser(id);
        return new Result(200 ,"删除成功");
    }

    @GetMapping("/json/{id}")
    public String getJackson(@PathVariable Integer id){
        User userById = userService.getUserById(id);
        Date date = new Date();
        String json = JsonUtils.getJson(userById);
        String json1 = JsonUtils.getJson(date);
        return json + json1;
    }

}
