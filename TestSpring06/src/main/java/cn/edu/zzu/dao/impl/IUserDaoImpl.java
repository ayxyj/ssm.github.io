package cn.edu.zzu.dao.impl;

import cn.edu.zzu.dao.IUserDao;
import cn.edu.zzu.domain.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Repository("userDao")
//@Repository
public class IUserDaoImpl implements IUserDao {

    @Override
    public void saveUser(User user) {
        System.out.println("持久化user："+user);
    }


    @PreDestroy
    public void destory(){
        System.out.println("对象被销毁了");
    }
    @PostConstruct
    public void init(){
        System.out.println("对象初始化了！");
    }
}
