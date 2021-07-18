package cn.edu.zzu.service.impl;

import cn.edu.zzu.dao.IUserDao;
import cn.edu.zzu.dao.impl.IUserDaoImpl;
import cn.edu.zzu.domain.User;
import cn.edu.zzu.factory.BeanFactory;
import cn.edu.zzu.service.IUserService;

public class IUserServiceImpl implements IUserService {

    @Override
    public void saveUser(User user) {
//        IUserDao userDao = new IUserDaoImpl();
        BeanFactory factory = new BeanFactory();
        IUserDao userDao =(IUserDao) factory.getBean("userDao");
        userDao.saveUser(user);

    }
}
