package cn.edu.zzu.dao.impl;

import cn.edu.zzu.dao.IUserDao;
import cn.edu.zzu.domain.User;

public class IUserDaoImpl implements IUserDao {

    @Override
    public void saveUser(User user) {
        System.out.println("持久化user："+user);
    }
}
