package cn.edu.zzu;

import cn.edu.zzu.domain.User;
import cn.edu.zzu.mapper.IUserMapper;
import cn.edu.zzu.service.IUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringTestMapper {
    @Test
    public void test(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        IUserMapper bean = ac.getBean(IUserMapper.class);
        List<User> all = bean.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }
    @Test
    public void test1(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        IUserService bean = applicationContext.getBean(IUserService.class);
        List<User> a = bean.findUserByName("a");
        for (User user : a) {
            System.out.println(user);
        }
    }
}
