package cn.edu.zzu;

import cn.edu.zzu.QueryVO.QueryVo;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class test {
    InputStream in = null;
    SqlSession sqlSession = null;
    IUserDao iUserDao = null;

    @Before
    public void init() throws Exception{
        //加载配置文件
         in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //构建者模式  工厂模式
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //创建sqlsession对象
        sqlSession = factory.openSession();//openSession(true)  事务进行自动提交(仅仅在单操作时使用，类似转账不可以)
        //创建代理对象
        iUserDao = sqlSession.getMapper(IUserDao.class);
    }
    @After
    public void destory() throws Exception {
        //涉及到cud,需要进行事物的提交
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }
    //----------------------------动态sql操作--------------------------
    @Test
    public void findByUserIds(){
        QueryVo vo = new QueryVo();
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(41);
        ids.add(42);
        vo.setIds(ids);
        List<User> userByIds = iUserDao.findUserByIds(vo);
        for(User user : userByIds){
            System.out.println(user);
        }
    }
    @Test
    public void findUserByCondition(){
        User u = new User();
        u.setUsername("老王");
        u.setSex("女");
        List<User> users = iUserDao.findUserByCondition(u);
        for(User user : users){
            System.out.println(user);
        }
    }
    //----------------------------插入操作--------------------------

    /**
     * 插入后查询插入的id信息，通过selectKey标签
     */
    @Test
    public void saveUser(){
        User user = new User();
        user.setUsername("zzu_02");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("河南");
        System.out.println("插入后："+user);
        iUserDao.saveUser(user);
        System.out.println("插入后："+user);
    }

    //----------------------------更新操作--------------------------

    /**
     * 根据id进行信息修改
     */
    @Test
    public void UpdateUser(){
        User user = new User();
        user.setId(50);
        user.setUsername("zzu_update");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("河南");
        iUserDao.UpdateUserById(user);
    }
    //----------------------------删除操作--------------------------
    @Test
    public void deleteById(){
        iUserDao.deleteById(50);
    }

    @Test
    public void deleteByIdFLag(){
        int count = iUserDao.deleteByIdFLag(51);
        if(count != 0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }
    //----------------------------查询操作--------------------------
    /**
     * 查询所有
     */
    @Test
    public void findAll(){
        //执行方法
        List<User> users = iUserDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
    }

    /**
     * 根据id查询
     */
    @Test
    public void findById(){
        User user = iUserDao.findById(41);
        System.out.println(user);
    }

    /**
     * 模糊查询
     */
    @Test
    public void findByLike(){
        List<User> users =  iUserDao.findByLike("%王%");
        for(User user : users){
            System.out.println(user);
        }
    }

    /**
     * 测试使用QueryVo作为查询条件
     */
    @Test
    public void QueryVO(){
        QueryVo queryVo = new QueryVo();
        User voUser = new User();
        voUser.setUsername("%王%");
        queryVo.setUser(voUser);
        List<User> users =iUserDao.QueryVo(queryVo);
        for(User user : users){
            System.out.println(user);
        }
    }

    /**
     * 聚合函数总条数
     */
    @Test
    public  void findTotal(){
        int count = iUserDao.findTotal();
        System.out.println(count);
    }

}
