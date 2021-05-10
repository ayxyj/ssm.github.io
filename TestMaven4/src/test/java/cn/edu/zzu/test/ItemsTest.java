package cn.edu.zzu.test;

import cn.edu.zzu.domain.Items;
import cn.edu.zzu.mapper.ItemsDao;
import cn.edu.zzu.services.ItemsServices;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ItemsTest {
    @Test
    public void findById(){
        //获取spring容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从容器中拿到所需要的的dao的代理对象
        ItemsDao itemsDao = ac.getBean(ItemsDao.class);
        //调用方法
        Items items = itemsDao.findById(1);
        System.out.println(items.getName());

        //services测试
        ItemsServices itemsServices = ac.getBean(ItemsServices.class);
        //调用方法
        Items items2 = itemsServices.findById(2);
        System.out.println(items2.getName());
    }
}
