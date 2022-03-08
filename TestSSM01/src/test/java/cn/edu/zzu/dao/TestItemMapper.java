package cn.edu.zzu.dao;

import cn.edu.zzu.config.SpringConfiguration;
import cn.edu.zzu.entity.Item;
import cn.edu.zzu.mapper.IItemMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试Mybatis框架，需要整合SpringJunit，不然拿不到容器中的对象
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/spring-dao.xml")
@ContextConfiguration(classes = SpringConfiguration.class)
//@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestItemMapper {

    InputStream in = null ;
    SqlSessionFactory factory = null ;
    SqlSession sqlSession = null ;

    @Test
    public void test(){
        IItemMapper mapper = sqlSession.getMapper(IItemMapper.class);
        List<Item> allItems = mapper.findAllItems();
        System.out.println(allItems);
    }


    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        sqlSession = factory.openSession();
    }
    @After
    public void destory() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }
}
