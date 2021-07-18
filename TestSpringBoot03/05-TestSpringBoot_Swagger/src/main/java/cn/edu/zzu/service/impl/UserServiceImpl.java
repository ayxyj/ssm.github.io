package cn.edu.zzu.service.impl;

import cn.edu.zzu.entity.User;
import cn.edu.zzu.mapper.IUserMapper;
import cn.edu.zzu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements IUserService {
    //dao对象
    @Autowired
    private IUserMapper iUserMapper;
    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> findAllUsers() {
        return iUserMapper.findAllUsers();
    }
}
