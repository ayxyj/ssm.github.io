package cn.edu.zzu.service.impl;

import cn.edu.zzu.domain.User;
import cn.edu.zzu.mapper.IUserMapper;
import cn.edu.zzu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserMapper iUserMapper;

    @Override
    public List<User> findAll() {
        return iUserMapper.findAll();
    }

    @Override
    public User findUserById(Integer id) {
        return iUserMapper.findUserById(id);
    }

    @Override
    public User findUserByUid(String uid) {
        return iUserMapper.findUserByUid(uid);
    }

    @Override
    public int deleteUserById(Integer id) {
        return iUserMapper.deleteUserById(id);
    }

    @Override
    public int updateUser(User user) {
        return iUserMapper.updateUser(user);
    }

    @Override
    public int insertUser(User user) {
        return iUserMapper.insertUser(user);
    }
}
