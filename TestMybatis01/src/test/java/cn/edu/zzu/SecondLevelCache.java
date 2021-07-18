package cn.edu.zzu;

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
    InputStream in = null ;
    SqlSessionFactory factory = null;
    @Test
    public void findById(){
        //使用sqlSession创建dao接口代理对象
        SqlSession sqlSession1 = factory.openSession();
        IUserDao mapper = sqlSession1.getMapper(IUserDao.class);
        User user = mapper.findById(1);
        System.out.println(user);
        //==================测试一级缓存=====================
        User user1 = mapper.findById(1);
        System.out.println(user1);
        System.out.println("===================================================一级缓存============================================");
        System.out.println(user == user1);
        sqlSession1.clearCache();//清空一级缓存
        //====================一级缓存失效================
        User user2 = mapper.findById(1);
        System.out.println(user2);
        System.out.println("===================================================一级缓存失效，clearCache============================================");
        System.out.println(user == user2);

        sqlSession1.close(); //关闭傻sqlSession1

        //=================测试二级缓存==================
        SqlSession sqlSession2 = factory.openSession();
        IUserDao mapper1 = sqlSession2.getMapper(IUserDao.class);
        User user3 = mapper1.findById(1);
        System.out.println(user3);
        System.out.println("===================================================二级缓存============================================");
        System.out.println(user == user3);

        sqlSession2.close(); //关闭傻sqlSession2
    }
    @Before
    public void Init() throws Exception{
        //加载配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //构建者模式  工厂模式
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
    }
    @After
    public void Destory() throws Exception{
        in.close();
    }
}
