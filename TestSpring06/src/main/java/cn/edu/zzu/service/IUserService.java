package cn.edu.zzu.service;

import cn.edu.zzu.domain.User;
import org.springframework.stereotype.Component;


public interface IUserService {
    void saveUser(User user);
}
