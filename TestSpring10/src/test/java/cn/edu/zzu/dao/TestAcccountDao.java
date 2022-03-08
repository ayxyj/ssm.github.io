package cn.edu.zzu.dao;

import cn.edu.zzu.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class TestAcccountDao {
    @Autowired
    private IAccountDao ao ;

    @Test
    public void findALl(){
        List<Account> all = ao.findAll();
        for (Account ac : all){
            System.out.println(ac);
        }
    }

    @Test
    public void findByName(){
        List<Account> all = ao.findAccountByName("test");
        for (Account ac : all){
            System.out.println(ac);
        }
    }

    @Test
    public void findByNameLike(){
        List<Account> all = ao.findAccountByNameLike("%test%");
        for (Account ac : all){
            System.out.println(ac);
        }
    }
}
