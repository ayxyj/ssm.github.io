package cn.edu.zzu.service;

import cn.edu.zzu.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    public static User getUserById(Integer id) {
        User user = new User();
        user.setId(id);
        user.setUsername("zzu");
        user.setAddress("河南");
        user.setBirthday(new Date());
        return user;
    }

    public void add(User user) {
        System.out.println(user);
    }

    public void updateUser(User user) {
        System.out.println(user);
    }

    public void deleteUser(String id) {
        System.out.println(id);
    }
}
