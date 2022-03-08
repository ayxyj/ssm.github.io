package cn.edu.zzu.dao;

import cn.edu.zzu.dao.impl.IUserDaoImpl;
import cn.edu.zzu.domain.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;


public class MybatisTest {
    @Test
    public void testFindALl() throws  Exception{
        //1.读取配置文件
        //2.创建sqlsessionfactory工厂
        //3.使用工厂生产sqlsession对象
        //4.使用sqlsesssion创建Dao接口得代理对象
        //5.使用代理对象执行方法
        //6.释放资源
//        ResourceBundle resourceBundle = ResourceBundle.getBundle("SqlMapConfig.xml");

        //加载资源配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //获取工厂对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);


        //获取sqlsession对象
        //SqlSession sqlSession = factory.openSession();
        //创建代理对象
        //IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        //创建sqlsession和接口实现类对象
        IUserDaoImpl userDao = new IUserDaoImpl(factory);


        //执行方法
        List<User> users = userDao.findAll();
        for (User user :users) {
            System.out.println(user);
        }
        //释放资源
       // sqlSession.close();
        in.close();
    }

}
