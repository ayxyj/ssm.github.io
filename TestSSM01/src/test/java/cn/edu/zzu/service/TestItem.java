package cn.edu.zzu.service;

import cn.edu.zzu.entity.Item;
import cn.edu.zzu.service.impl.ItemServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;



public class TestItem {


    @Test
    public void test() throws IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ItemServiceImpl bean = ac.getBean(ItemServiceImpl.class);
        ItemServiceImpl itemService = (ItemServiceImpl) ac.getBean("itemServiceImpl");
        List<Item> allItems = bean.findAllItems();
        for (Item item : allItems){
            System.out.println(item);
        }
    }

}
