package cn.edu.zzu.dao;

import cn.edu.zzu.domain.Account;
import cn.edu.zzu.domain.AccountUser;
import cn.edu.zzu.domain.Role;
import cn.edu.zzu.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.InputStream;
import java.util.List;


public class MybatisTest {
    InputStream in ;
    SqlSession session;
    @Before
    public void init() throws Exception{
        //加载配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //构建者模式  工厂模式
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //创建sqlsession对象
        session = factory.openSession();//openSession(true)  事务进行自动提交(仅仅在单操作时使用，类似转账不可以)
    }

    /**
     * 单表查询所有信息
     * @throws Exception
     */
    @Test
    public void testFindALl() throws  Exception{
        IAccountDao iAccountDao = session.getMapper(IAccountDao.class);
        List<Account> all = iAccountDao.findAll();
        for(Account acc : all){
            System.out.println(acc);
        }
    }

    /**
     * 一对一新建accountUser,查询账户对应的用户名和地址   1:1
     * @throws Exception
     */
    @Test
    public  void testFindAccountUser(){
        IAccountDao iAccountDao = session.getMapper(IAccountDao.class);
        List<AccountUser> aus = iAccountDao.findAccountUser();
        for(AccountUser au : aus){
            System.out.println(au);
        }
    }

    /***
     * 讲user信息封装到account对象中去  1:1
     * @throws Exception
     */
    @Test
    public void findAccountAndUser(){
        IAccountDao iAccountDao = session.getMapper(IAccountDao.class);
        List<Account> aus = iAccountDao.findAccountAndUser();
        for(Account aau :aus){
            System.out.println(aau);
            System.out.println(aau.getUser());
        }
    }

    /**
     * 1:n 查询user 对应得account信息
     * @throws Exception
     */
    @Test
    public  void findUserAndAccounts(){
        IUserDao iuserDao = session.getMapper(IUserDao.class);
        List<User> userAndAccounts = iuserDao.findUserAndAccounts();
        for (User user : userAndAccounts){
            System.out.println(user);
        }
    }

    /**
     * 查找role
     * @throws Exception
     */
    @Test
    public void findAllRole(){
        IRoleDao mapper = session.getMapper(IRoleDao.class);
        List<Role> all = mapper.findAll();
        for (Role role :all) {
            System.out.println(role);
        }
    }

    /**
     * 查询角色对应的应用 m:n
     */
    @Test
    public void findRoleAndUser(){
        IRoleDao mapper = session.getMapper(IRoleDao.class);
        List<Role> roles = mapper.findRoleAndUsers();
        for(Role role :roles){
            System.out.println(role);
        }
    }

    /**
     * 查询用户对应得角色 m:n
     * @throws Exception
     */
    @Test
    public  void findUserAndRoles(){
        IUserDao mapper = session.getMapper(IUserDao.class);
        List<User> userAndRoles = mapper.findUserAndRoles();
        for (User user : userAndRoles){
            System.out.println(user);
        }
    }
    @After
    public void destory() throws Exception{
        session.commit();
        session.close();
        in.close();
    }
}
