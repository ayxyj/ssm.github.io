package cn.edu.zzu.service.impl;

import cn.edu.zzu.domain.User;
import cn.edu.zzu.mapper.IUserMapper;
import cn.edu.zzu.queryVo.UserQueryVo;
import cn.edu.zzu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserMapper iUserMapper ;

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> findAll() {
        return iUserMapper.findAll();
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @Override
    public int insertUser(User user) {
        int i = iUserMapper.insertUser(user);
        return i;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @Override
    public int updateUser(User user) {
        return iUserMapper.updateUser(user);
    }

    @Override
    public List<User> findUserByName(String username) {
        String name = '%'+username+'%';
        List<User> users =  iUserMapper.findUserByName(name);
        return users;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public User findUserById(Integer id) {
        User userById = iUserMapper.findUserById(id);
        return userById;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public int deleteUserById(Integer id) {
        return iUserMapper.deleteUserById(id);
    }

    /**
     * 根据QueryVo查询用户
     * @param userQueryVo
     * @return
     */
    @Override
    public User findUserByQueryVo(UserQueryVo userQueryVo) {
        User user = iUserMapper.findUserByQueryVo(userQueryVo);
        return user;
    }
}
