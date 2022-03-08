package cn.edu.zzu.mapper;

import cn.edu.zzu.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserMapper {
    /**
     * find all
     * @return
     */
    List<User>  findAll();
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
