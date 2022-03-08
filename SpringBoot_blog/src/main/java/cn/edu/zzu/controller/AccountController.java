package cn.edu.zzu.controller;

import cn.edu.zzu.common.dto.LoginDto;
import cn.edu.zzu.common.lang.Result;
import cn.edu.zzu.entity.User;
import cn.edu.zzu.service.UserService;
import cn.edu.zzu.util.JwtUtils;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    /**
     * 登录
     * @param loginDto
     * @param httpServletResponse
     * @return
     */
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        //断言处理
        Assert.notNull(user, "用户不存在");

        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            System.out.println(SecureUtil.md5(loginDto.getPassword()));
            System.out.println(user.getPassword());
            return Result.fail("密码错误！");
        }
        String jwt = jwtUtils.generateToken(user.getId());

        httpServletResponse.setHeader("Authorization", jwt);
        httpServletResponse.setHeader("Access-control-Expose-Headers", "Authorization");
        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar" , user.getAvatar())
                .put("email" , user.getEmail()).map()
        );
    }

    /**
     * 退出
     * @return
     */
    @RequiresAuthentication
    @GetMapping("logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }
}
