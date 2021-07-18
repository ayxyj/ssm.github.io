package cn.edu.zzu;

import cn.edu.zzu.service.IAccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAccountService {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:bean.xml");
        IAccountService accountService = ac.getBean("accountServiceImpl", IAccountService.class);
        accountService.saveAccount();
    }
}
