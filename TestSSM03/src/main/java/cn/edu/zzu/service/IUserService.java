package cn.edu.zzu.service;

import cn.edu.zzu.domain.User;
import cn.edu.zzu.queryVo.UserQueryVo;

import java.util.List;

public interface IUserService {
    /**
     * 查询所有用户
     */
    List<User> findAll();
    /**
     * 添加用户
     */
    int insertUser(User user);
    /**
     * 更新用户信息
     */
    int updateUser(User user);
    /**
     * 根据姓名检索
     */
    List<User> findUserByName(String username);
    /**
     * 根据id查询用户
     */
    User findUserById(Integer id);
    /**
     * 根据id删除用户
     */
    int deleteUserById(Integer id);

    User findUserByQueryVo(UserQueryVo userQueryVo);
}
