package cn.edu.zzu.service.impl;

import cn.edu.zzu.dao.IUserDao;
import cn.edu.zzu.dao.impl.IUserDaoImpl;
import cn.edu.zzu.domain.User;
import cn.edu.zzu.service.IUserService;

public class IUserServiceImpl implements IUserService {

    public IUserServiceImpl(){
        System.out.println("IUserServiceImpl被创建！！！！");
    }
    @Override
    public void saveUser(User user) {
        IUserDao userDao = new IUserDaoImpl();
        userDao.saveUser(user);
    }
}
