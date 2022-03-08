package cn.edu.zzu.dao;


import cn.edu.zzu.domain.User;

import java.util.List;

public interface IUserDao {
    /**
     * 用户持久层接口：查询所有用户
     * @return
     */
    public List<User> findAll();

    /**
     * 1:n  查询用户信息对应得account信息
     */
    List<User> findUserAndAccounts();
    /**
     * 查询用户对应得角色 m:n
     */
    List<User> findUserAndRoles();
}
