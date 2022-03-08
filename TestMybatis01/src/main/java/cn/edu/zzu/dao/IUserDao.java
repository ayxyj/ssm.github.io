package cn.edu.zzu.dao;

import cn.edu.zzu.domain.User;
import cn.edu.zzu.queryvo.QueryVo;

import java.util.List;

public interface IUserDao {
    //查询所有
    List<User> findAll();
    //根据id查询
    User findById(int id);
    //增加一个用户,并返回影响数据库记录的行数
    int saveUser(User user);
    //删除一个用户
    int deleteUserById(int id);
    //更新一个用户
    int updateUserById(User user);
    //模糊查询
    List<User> findByLike(String like);
    //查询条数
    int CountUser();

    //vo
    List<User> findByQueryVo(QueryVo vo);
    //condition
    List<User> findUserByCondition(User user);
    //list<integer> ids
    List<User> findUserByQueryVoIds(QueryVo vo);

    //1:n user:accounts
    List<User> findUserAndAccounts();
    //根据id去查询accounts
    List<User> findUserAndAccountsID(Integer id);
}
