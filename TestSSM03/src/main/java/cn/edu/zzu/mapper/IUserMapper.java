package cn.edu.zzu.mapper;

import cn.edu.zzu.domain.User;
import cn.edu.zzu.queryVo.UserQueryVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserMapper {
    /**
     * 查询所有用户信息
     * @return
     */
    List<User> findAll();
    /**
     * 增加一个用户
     */
    int insertUser(User user);
    /**
     * 删除一个用户根据主键
     */
    int deleteUserById(Integer id);
    /**
     * 修改一个用户
     */
    int updateUser(User user);
    /**
     * 查询一个用户
     */
    User findUserById(Integer id);
    /**
     * 查询条数
     */
    int findCount();

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    List<User> findUserByName(String username);

    /**
     * 根据vo查询
     * @param userQueryVo
     * @return
     */
    User findUserByQueryVo(UserQueryVo userQueryVo);
}
