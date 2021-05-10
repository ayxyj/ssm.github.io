package cn.edu.zzu;

import cn.edu.zzu.dao.IUserDao;
import cn.edu.zzu.dao.impl.IUserDaoImpl;
import cn.edu.zzu.domain.User;
import cn.edu.zzu.queryvo.QueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestUser {
    InputStream in = null ;
    SqlSession sqlSession = null ;

    /**
     * 1:n
     */
    @Test
    public void TestfindUserAndAccounts(){
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<User> userAndAccounts = mapper.findUserAndAccounts();
        for(User user : userAndAccounts){
            System.out.println(user);
        }
    }
    @Test
    public void findUserByQueryVoIds(){
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        QueryVo vo = new QueryVo();
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        vo.setIds(list);
        List<User> byQueryVo = mapper.findUserByQueryVoIds(vo);
        for (User u1 : byQueryVo){
            System.out.println(u1);
        }
    }
    @Test
    public void findByQueryVo(){
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setUsername("%zzu%");
        QueryVo vo = new QueryVo();
        vo.setUser(user);
        List<User> byQueryVo = mapper.findByQueryVo(vo);
        for (User u1 : byQueryVo){
            System.out.println(u1);
        }
    }
    @Test
    public void CountUser(){
        //使用sqlSession创建dao接口代理对象
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        int i = mapper.CountUser();
        System.out.println(i);
    }
    @Test
    public void testFind() {
        IUserDaoImpl userDaoImpl = new IUserDaoImpl();
        List<User> all = userDaoImpl.findAll();
        System.out.println(all);
    }
    @Test
    public void FindUserLike(){
        //使用sqlSession创建dao接口代理对象
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<User> all = mapper.findByLike("%zzu%");
        for (User user : all){
            System.out.println(user);
        }
    }
    @Test
    public void FindAll(){
        //使用sqlSession创建dao接口代理对象
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<User> all = mapper.findAll();
        for (User user : all){
            System.out.println(user);
        }
    }
    @Test
    public void FindUserByCondition(){
        //使用sqlSession创建dao接口代理对象
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setUsername("zzu_04");
        user.setSex("男");//多条件查询
        List<User> userByCondition = mapper.findUserByCondition(user);
        for (User user1 : userByCondition){
            System.out.println(user1);
        }
    }
    @Test
    public void findById(){
        //使用sqlSession创建dao接口代理对象
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User user = mapper.findById(1);
        System.out.println(user);
    }
    @Test
    public void saveuser(){
        //使用sqlSession创建dao接口代理对象
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setUsername("zzu_04");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("北京");
        //插入并返回影响数据库记录条数以及插入的主键id
        int i = mapper.saveUser(user);
        System.out.println(user);
        System.out.println(i);
    }
    @Test
    public void UpdateUserById(){
        //使用sqlSession创建dao接口代理对象
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setId(7);
        user.setUsername("zzu_04");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("北京");
        //插入并返回影响数据库记录条数以及插入的主键id
        int i = mapper.updateUserById(user);
        System.out.println(user);
        System.out.println(i);
    }
    @Test
    public void DeleteUser(){
        //使用sqlSession创建dao接口代理对象
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        int i = mapper.deleteUserById(6);
        System.out.println(i);
    }
    @Before
    public void Init() throws Exception{
        //加载配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //构建者模式  工厂模式
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //生产sqlSession
        sqlSession = factory.openSession();
    }
    @After
    public void Destory() throws Exception{
        //事务提交
        sqlSession.commit();
        in.close();
        sqlSession.close();
    }
}
