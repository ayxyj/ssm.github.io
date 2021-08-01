package cn.edu.zzu;

import cn.edu.zzu.domain.User;
import cn.edu.zzu.mapper.IUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class TestMapper {

    SqlSession sqlSession = null;
    InputStream in = null;

    @Test
    public void  findCount(){
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        System.out.println(mapper.findCount());
        User userById = mapper.findUserById(5);
        System.out.println(userById);
    }

    @Test
    public void updateUser() throws ParseException {
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        User user = new User();
        user.setId(5);
        user.setUsername("测试1");
        user.setPassword("123456");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date().getTime());
//        user.setBirthday(new Timestamp(new Date().getTime()));
        user.setBirthday(simpleDateFormat.parse(format));
        user.setVal(29.888);
        int i = mapper.updateUser(user);
        findAll();
    }

    @Test
    public void deleteById(){
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        int i = mapper.deleteUserById(4);
        System.out.println(i);
        findAll();
    }

    @Test
    public void findAll() {
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        List<User> all = mapper.findAll();
        print(all);
    }

    @Test
    public void insert() throws ParseException {
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        User user = new User();
        user.setUsername("测试1");
        user.setPassword("123456");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date().getTime());
//        user.setBirthday(new Timestamp(new Date().getTime()));
        user.setBirthday(simpleDateFormat.parse(format));
        user.setVal(19.888);
        int i = mapper.insertUser(user);
        System.out.println(i);
    }

    private void print(List<User> all) {
        for (User user : all) {
            System.out.println("=====User:" + user);
        }
    }


    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = builder.build(in);
        sqlSession = build.openSession(true);//commit
    }

    @After
    public void destory() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

}
