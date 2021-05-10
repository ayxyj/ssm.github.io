package cn.edu.zzu.dao;


import cn.edu.zzu.domain.User;

import java.util.List;

public interface IUserDao {
    /**
     * 用户持久层接口：查询所有用户
     * @return
     */
    public List<User> findAll();
}
