package cn.edu.zzu;

import cn.edu.zzu.config.SpringConfiguration;
import cn.edu.zzu.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
//@ContextConfiguration(classes = SpringConfiguration.class)
public class TesrAccountService {

    @Autowired
    IAccountService as ;

    @Test
    public void testAccountService(){
        as.saveAccount();
    }

}
