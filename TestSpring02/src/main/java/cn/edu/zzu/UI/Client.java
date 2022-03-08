package cn.edu.zzu.UI;

import cn.edu.zzu.domain.User;
import cn.edu.zzu.service.IUserService;
import cn.edu.zzu.service.impl.IUserServiceImpl;

import java.util.Date;

public class Client {
    public static void main(String[] args) {
        IUserService userService = new IUserServiceImpl();
        User user = new User();
        user.setId(1);
        user.setUsername("zzu");

        user.setSex("男");
        user.setAddress("河南");
        user.setBirthday(new Date());
        userService.saveUser(user);
    }
}
