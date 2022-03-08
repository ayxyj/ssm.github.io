package cn.edu.zzu.UI;

import cn.edu.zzu.dao.IUserDao;
import cn.edu.zzu.domain.User;
import cn.edu.zzu.service.IUserService;
import cn.edu.zzu.service.impl.IUserServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.sql.Timestamp;
import java.util.Date;

public class Client {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        //获取Spring容器方式
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");//ClassPathXmlApplicationContext加载配置文件后立刻创建对象
        IUserServiceImpl iUserServiceImpl = (IUserServiceImpl) ac.getBean("userService");
        IUserServiceImpl iUserServiceImpl2 = (IUserServiceImpl) ac.getBean("userService");
        System.out.println(iUserServiceImpl2 == iUserServiceImpl);//多例
        User user = new User();
        user.setUsername("zzu_01");
        user.setBirthday(new Timestamp(new Date().getTime()));
        iUserServiceImpl.saveUser(user);

        IUserDao userDao = ac.getBean("userDao", IUserDao.class);
        IUserDao userDao1 = ac.getBean("userDao", IUserDao.class);
        System.out.println(userDao == userDao1);//单例
        ac.close();
    }
}
