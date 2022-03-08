package cn.edu.zzu.service;

import cn.edu.zzu.domain.User;

import java.util.List;

public interface IUserService {
    /**
     * find all
     * @return
     */
    List<User> findAll();
    /**
     * findUserById
     */
    User findUserById(Integer id);
    /**
     * findUserByName
     */
    User findUserByUid(String uid);




    /**
     * delete
     */
    int deleteUserById(Integer id);
    /**
     * update
     */
    int updateUser(User user);
    /**
     * insert
     */
    int insertUser(User user);
}
