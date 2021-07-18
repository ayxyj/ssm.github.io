package cn.edu.zzu.service;

import cn.edu.zzu.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class TestAccountService {
    /**
     * 注解匹配： 先配置类型Map ： IAccountService 在value中查找命名，若存在多个，且名称没有匹配上就会报错（found 2 ） ，解决方法：
     *  1.Qualifier
     *  2.命名配置
     */
    //    @Qualifier("accountServiceImpl")
    @Autowired
    @Qualifier("accountServiceImpl_proxy")
    private  IAccountService accountServiceImpl ;


    @Test
    public  void  findAll(){
        List<Account> allAccount = accountServiceImpl.findAllAccount();
        for (Account ac :allAccount){
            System.out.println(ac);
        }
    }

    @Test
    public void Transfer(){
        accountServiceImpl.transfer("test1" , "test2" , 100.0f);
    }


}
