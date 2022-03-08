package cn.edu.zzu;

import cn.edu.zzu.dao.IRoleDao;
import cn.edu.zzu.dao.IUserDao;
import cn.edu.zzu.domain.Role;
import cn.edu.zzu.domain.User;
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

public class TestRole {
    InputStream resourceAsStream = null ;
    SqlSession sqlSession = null ;

    /**
     * m:n roles:users
     */
    @Test
    public void TestFindRolesAndUsers(){
        IRoleDao IRoleDaoImpl = sqlSession.getMapper(IRoleDao.class);
        IUserDao IUserDaoImpl = sqlSession.getMapper(IUserDao.class);
        List<Role> rolesAndUsers = IRoleDaoImpl.findRolesAndUsers();
        for (Role r : rolesAndUsers){
            //根据用户id去查询账户
            List<User> users = r.getUsers();
            List<User> userAndAccounts = null;
            for (User u :users){
                Integer id = u.getId();
                userAndAccounts = IUserDaoImpl.findUserAndAccountsID(id);
                for (User u1 : userAndAccounts){
                    u.setAccounts(u1.getAccounts());
                }
            }
            System.out.println(r);
        }
    }
    @Before
    public void Init() throws IOException {
        resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(resourceAsStream);
        sqlSession = factory.openSession(true);
    }

    @After
    public void Destory() throws IOException {
        resourceAsStream.close();
        sqlSession.close();
    }
}
