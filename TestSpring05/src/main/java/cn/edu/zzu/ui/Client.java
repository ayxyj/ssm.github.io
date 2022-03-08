package cn.edu.zzu.ui;

import cn.edu.zzu.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService =(IAccountService) ac.getBean("accountService");
        accountService.saveAccount();

        ClassPathXmlApplicationContext ac1 = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService1 =(IAccountService)ac1.getBean("accountService2");
        accountService1.saveAccount();

        ClassPathXmlApplicationContext ac2 = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService3 =(IAccountService) ac2.getBean("accountService3");
        accountService3.saveAccount();
    }
}
