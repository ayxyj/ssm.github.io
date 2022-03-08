package cn.edu.zzu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Api("用户操作接口")
@ResponseBody
public class UserController {

    @GetMapping("/hello")
    @ApiOperation(tags = "testTags" , notes = "TestNotes", value = "TestValue")
    public String test(){
        return "<h1>hello MVC! 哈哈</h1>";
    }


}
