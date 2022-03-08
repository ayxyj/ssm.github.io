package cn.edu.zzu.dao;

import cn.edu.zzu.QueryVO.QueryVo;
import cn.edu.zzu.domain.User;

import java.util.List;

/**
 * User的持久化层接口
 */
public interface IUserDao {
    //查询所有（注解【删除xml所在的包，不然找不到接口】和xml两种方式）
    //@Select("select * from user")
    List<User> findAll();
    //根据id查询
    User findById(int i);
    //模糊查询
    List<User> findByLike(String s);
    //查询总得条数
    int findTotal();
    //插入用户,并返回id
    void saveUser(User user);
    //更新用户
    void UpdateUserById(User user);
    //删除用户
    void deleteById(int i);
    //返回删除信息
    int deleteByIdFLag(int i);

    //测试使用QueryVo作为查询条件
    List<User> QueryVo(QueryVo queryVo);
    //动态sql
    //根据传入参数条件进行查询
    List<User> findUserByCondition(User user);
    List<User> findUserByIds(QueryVo vo);
}
