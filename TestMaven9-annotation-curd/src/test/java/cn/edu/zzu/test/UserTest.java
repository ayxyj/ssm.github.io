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
import java.util.Date;
import java.util.List;

public class UserTest {
    InputStream in = null;
    SqlSessionFactory factory = null;
    SqlSession sqlSession = null;
    @Before
    public void init() throws  Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        sqlSession = factory.openSession();
    }
    @After
    public void destory() throws  Exception{
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }
    /**
     * 1:n  一级缓存
     */
    @Test
    public  void TestOne2Many(){
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<User> userAndAccounts = mapper.findUserAndAccounts();
        for (User user : userAndAccounts){
            System.out.println(user);
        }
    }

    /**
     * 1:1
     */
    @Test
    public void testOne2One(){
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<User> userAccount = mapper.findUserAccount();
        for (User user :userAccount){
            System.out.println(user);
        }
    }

    //增加 修改  删除
    @Test
    public void TestInsert(){
        User user = new User();
        user.setUsername("zzu_05");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("河南");

        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        mapper.InsertUser(user);
        System.out.println(user);
    }

    @Test
    public void UpdateUser(){
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User user1 = new User();
        user1.setId(2);
        user1.setUsername("zzu_02");
        user1.setBirthday(new Date());
        user1.setSex("女");
        user1.setAddress("河南");

        mapper.UpdateUser(user1);
    }

    @Test
    public void DeleteUser(){
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        mapper.DeleteUserById(4);
    }

    //聚合函数
    @Test
    public void CountUsers(){
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        int i = mapper.CountUsers();
        System.out.println(i);
    }

    //----------------查找
    @Test
    public void findAll(){
        IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
        List<User> all = iUserDao.findAll();
        for (User user : all){
            System.out.println(user);
        }
    }

    @Test
    public void findById(){
        IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
        User byId = iUserDao.findById(41);
        System.out.println(byId);
    }

    @Test
    public void findLikeName(){
        IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
        List<User> likeUsername = iUserDao.findLikeUsername("%王%");
        for (User user : likeUsername){
            System.out.println(user);
        }
    }
}
