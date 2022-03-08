package cn.edu.zzu;

import cn.edu.zzu.dao.IAccountDao;
import cn.edu.zzu.doamin.Account;
import cn.edu.zzu.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class TestAccount {

    @Autowired
    private IAccountDao accountDao;

    @Autowired
    private IAccountService accountService;

    @Test
    public void  test(){
        Account test1 = accountDao.findAccountByName("test1");
        test1.setMoney(1001.0f);
        accountDao.UpdateAccount(test1);
        test1 = accountDao.findAccountByName("test1");
        System.out.println(test1);
    }
    @Test
    public void testfindAll(){
        List<Account> all = accountService.findAll();
        for(Account ac : all){
            System.out.println(ac);
        }
    }
    @Test
    public void transfer(){
        accountService.transfer("test1","test2",100);
        List<Account> all = accountService.findAll();
        for(Account ac : all){
            System.out.println(ac);
        }
    }
}
