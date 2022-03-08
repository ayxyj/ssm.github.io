package cn.edu.zzu.controller;

import cn.edu.zzu.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 查询所有用户
     * 资源的查操作
     * @return
     */
    @GetMapping("/findAllUsers")
    @ApiOperation("查询所有用户")
    public String findAllUsers(){
        User user = new User(1, "test", "test", new Date(), "test", "test");
        return user.toString();
    }

    /**
     * 添加一个用户
     * Post请求不会覆盖前一个请求，通常用来增加资源
     * @param user
     * @return
     */
    @PostMapping("/add")
    public String addUser(@RequestBody @ApiParam("Json数据类型")User user){
        return user.toString();
    }

    /**
     * 查询一个用户
     * 后一个请求会把前一个请求覆盖，通常用来改资源
     */
    @PutMapping("/get/{id}")
    public User getUser(@PathVariable Integer id){
        return new User(id,"getUser","123456",new Date(),"男","bj");
    }
}
