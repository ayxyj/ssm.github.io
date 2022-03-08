package cn.edu.zzu.service;

import cn.edu.zzu.entity.User;

import java.util.List;

public interface IUserService {
    /**
     * 查询 所有用户
     * @return
     */
    List<User>  findAllUsers();
}
