package cn.edu.zzu.UI;

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

import java.util.Date;

public class Client {

    /**
     * 获取spring的Ioc核心容器，并根据id获取对象
     *
     * ApplicationContext的三个常用实现类：
     *      ClassPathXmlApplicationContext：它可以加载类路径下的配置文件，要求配置文件必须在类路径下。不在的话，加载不了。(更常用)
     *      FileSystemXmlApplicationContext：它可以加载磁盘任意路径下的配置文件(必须有访问权限）
     *
     *      AnnotationConfigApplicationContext：它是用于读取注解创建容器的。
     *
     * 核心容器的两个接口引发出的问题：
     *  ApplicationContext:     单例对象适用              采用此接口
     *      它在构建核心容器时，创建对象采取的策略是采用立即加载的方式。也就是说，只要一读取完配置文件马上就创建配置文件中配置的对象。
     *
     *  BeanFactory:            多例对象使用
     *      它在构建核心容器时，创建对象采取的策略是采用延迟加载的方式。也就是说，什么时候根据id获取对象了，什么时候才真正的创建对象。
     * @param args
     */
    public static void main(String[] args) {

        //获取Spring容器方式
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");//ClassPathXmlApplicationContext加载配置文件后立刻创建对象
        ApplicationContext ac1 = new FileSystemXmlApplicationContext("D:\\Java\\idea\\TestSpring03\\src\\main\\resources\\bean.xml");
        //获取对象方式
        IUserServiceImpl userService1 = (IUserServiceImpl)ac.getBean("userService");
        IUserServiceImpl userService = ac1.getBean(IUserServiceImpl.class);

        User user = new User();
        user.setId(1);
        user.setUsername("zzu");
        user.setSex("男");
        user.setAddress("河南");
        user.setBirthday(new Date());

        userService.saveUser(user);
        userService1.saveUser(user);

        //--------BeanFactory----------
        Resource resource = new ClassPathResource("bean.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        IUserServiceImpl as  = (IUserServiceImpl)factory.getBean("userService");//对象使用时候被创建
        System.out.println(as);
    }

    public static void test(){
        IUserService userService = new IUserServiceImpl();
        User user = new User();
        user.setId(1);
        user.setUsername("zzu");

        user.setSex("男");
        user.setAddress("河南");
        user.setBirthday(new Date());
        userService.saveUser(user);
    }
}
