package cn.edu.zzu;

import cn.edu.zzu.dao.IAccountDao;
import cn.edu.zzu.domain.Account;
import cn.edu.zzu.domain.AccountUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestAccount {
    SqlSession  sqlSession = null ;
    InputStream in = null ;

    /**
     * 1:1查询   user.id = account.uid
     */
    @Test
    public void TestFindAccountAndUser(){
        IAccountDao mapper = sqlSession.getMapper(IAccountDao.class);
        List<Account> accountAndUser = mapper.findAccountAndUser();
        for (Account account : accountAndUser){
            System.out.println(account);
        }
    }
    /**
     * 1:1查询   user.id = account.uid
     */
    @Test
    public void TestFindAccountUser(){
        IAccountDao mapper = sqlSession.getMapper(IAccountDao.class);
        List<AccountUser> accountUser = mapper.findAccountUser();
        for (AccountUser au : accountUser){
            System.out.println(au);
        }
    }
    @Before
    public void Init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = builder.build(in);
        sqlSession = build.openSession(true);//设置autoCommit
    }
    @After
    public void Destory() throws IOException {
        in.close();
        sqlSession.close();
    }

}
