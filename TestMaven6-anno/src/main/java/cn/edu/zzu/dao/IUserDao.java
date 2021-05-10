package cn.edu.zzu.dao;


import cn.edu.zzu.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserDao {
    /**
     * 用户持久层接口：查询所有用户
     * @return
     */
    @Select("select * from user")
    public List<User> findAll();
}
