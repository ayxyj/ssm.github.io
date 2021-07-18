package cn.edu.zzu.service;

import cn.edu.zzu.dao.IAccountDao;
import cn.edu.zzu.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class TestAccountService {
    @Autowired
    private IAccountService as ;

    @Test
    public void test(){
        List<Account> all = as.findAll();
        for (Account ac : all){
            System.out.println(ac);
        }
    }
    @Test
    public void transfer(){
        as.transfer("test","test2" , 100);
    }

}
