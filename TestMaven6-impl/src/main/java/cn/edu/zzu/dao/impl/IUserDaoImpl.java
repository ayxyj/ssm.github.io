package cn.edu.zzu.dao.impl;

import cn.edu.zzu.dao.IUserDao;
import cn.edu.zzu.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class IUserDaoImpl implements IUserDao {
    private SqlSessionFactory factory;

    public IUserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }

    /**
     * 使用自己的写的dao实现类，可以解释在mapper/dao.xml中的namespace和id,通过该配置可以找到具体是哪个接口的那个方法
     * @return
     */
    @Override
    public List<User> findAll() {

        //使用工厂创建sqlsession对象
        SqlSession session = factory.openSession();
        //使用sqlsession执行查询方法
        //xml和注解开发时候  代理对象生成后执行方法 ， 接口实现类执行方法，value具体到接口下的方法名称
        //即  value = mapper.xml文件下的  namespace + id
        List<User> users = session.selectList("cn.edu.zzu.dao.IUserDao.findAll");
        session.close();
        //返回结果
        return users;
    }
}
