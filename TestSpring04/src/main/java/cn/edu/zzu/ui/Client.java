package cn.edu.zzu.ui;

import cn.edu.zzu.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //静态方法创建对象
        IAccountService accountService = (IAccountService) ac.getBean("instanceFactory");
        accountService.saveAccount();
        System.out.println(accountService);

        //普通方法创建对象
        IAccountService accountService1 = (IAccountService) ac.getBean("accountService");
        accountService1.saveAccount();
        System.out.println(accountService1);

        //默认构造方法创建对象
        IAccountService accountService2 = (IAccountService) ac.getBean("accountService2");
        IAccountService accountService3 = (IAccountService) ac.getBean("accountService2");
        accountService2.saveAccount();
        System.out.println("单例模式"+accountService2);
        System.out.println("单例模式"+accountService3);

        //多例的
        IAccountService accountService4 = (IAccountService) ac.getBean("accountService3");
        IAccountService accountService5 = (IAccountService) ac.getBean("accountService3");
        accountService4.saveAccount();
        System.out.println("多例模式"+accountService4);
        System.out.println("多例模式"+accountService5);

        //作用范围
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService6 = (IAccountService)classPathXmlApplicationContext.getBean("accountService4");
        accountService6.saveAccount();
        //单例被销毁
        classPathXmlApplicationContext.close();

//        ClassPathXmlApplicationContext classPathXmlApplicationContext2 = new ClassPathXmlApplicationContext("bean.xml");
//        IAccountService accountService7 = (IAccountService)classPathXmlApplicationContext2.getBean("accountService5");
//        accountService7.saveAccount();
//        //多例没有销毁
//        classPathXmlApplicationContext2.close();

    }
}
