package cn.edu.zzu.test;

import cn.edu.zzu.dao.IUserDao;
import cn.edu.zzu.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class SecondLevelCache {

    InputStream in = null;
    SqlSessionFactory factory = null;
    @Before
    public void init() throws  Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
    }
    @After
    public void destory() throws  Exception{
        in.close();
    }

    /**
     * 一级缓存  sqlsessiob 区域中存储对象 ，该区域是一个Map ,SqlSession消失缓存消失（insert update delete也会导致一级缓存消失）
     * 二级缓存  sqlsessionFactory对象的缓存。由同一个sqlsessionFactory对象创建的sqlSession共享其缓存。  区域中存储数据  仅仅执行一次查询（log4j）
     * 一级缓存  同一对象
     * 二级缓存  不同对象（数据获取存储到新对象中）
     */
    @Test
    public void FindOne(){
        SqlSession sqlSession = factory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User byId = mapper.findById(1);
        System.out.println(byId);

        sqlSession.close();//清除一级缓存

        SqlSession sqlSession2 = factory.openSession();
        IUserDao mapper2 = sqlSession2.getMapper(IUserDao.class);
        User byId2 = mapper2.findById(1);
        System.out.println(byId2);

        sqlSession2.close();
    }

}
