package cn.edu.zzu.dao;

import cn.edu.zzu.dao.impl.AccountDaoImpl;
import cn.edu.zzu.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class TestAccountDao {

    @Autowired
    IAccountDao accountDao;
    @Test
    public void test(){
        List<Account> accountAll = accountDao.findAccountAll();
        for (Account ac : accountAll){
            System.out.println(ac);
        }
    }
}