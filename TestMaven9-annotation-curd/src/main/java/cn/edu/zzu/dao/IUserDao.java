package cn.edu.zzu.dao;

import cn.edu.zzu.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

//配置二级缓存
@CacheNamespace(blocking = true)
public interface IUserDao {
    /**
     * 1:n
     */
    @Select("select * from user")
    @Results(id = "userAndAccounts",value = {
            @Result(id = true , property = "id" ,column = "id"),
            @Result(property = "username" , column = "username"),
            @Result(property = "birthday" , column = "birthday"),
            @Result(property = "sex" , column = "sex"),
            @Result(property = "address" , column = "address"),
            @Result(column = "id",property = "accounts" ,many = @Many(select = "cn.edu.zzu.dao.IAccountDao.findById",fetchType = FetchType.LAZY)),
            @Result(column = "id",property = "account",one = @One(select = "cn.edu.zzu.dao.IAccountDao.findById",fetchType = FetchType.LAZY))
    })
    List<User> findUserAndAccounts();
    /**
     * 1:1
     */
    //SELECT u.*,a.id AS aid ,a.uid,a.money  FROM USER u ,account a WHERE u.id=a.uid
    @Select("select * from user")
    //解决字段名和表属性列名不一致和多表查询
    @Results(id="userMap", value = {
            @Result(id = true, property = "id" ,column = "id"),
            @Result(property = "username" , column = "username"),
            @Result(property = "birthday" , column = "birthday"),
            @Result(property = "sex" , column = "sex"),
            @Result(property = "address" , column = "address"),
            @Result(column = "id",property = "account",one = @One(select = "cn.edu.zzu.dao.IAccountDao.findById",fetchType = FetchType.LAZY))
    })
    List<User> findUserAccount();


    /***
     * 增加  修改  删除
     */

    @Insert("insert into user(username,birthday,sex,address) values(#{username},#{birthday},#{sex},#{address})")
    @SelectKey(keyColumn = "id" , keyProperty = "id", before = false, resultType = Integer.class, statement = {" select last_insert_id()"})
    int InsertUser(User user);

    @Update("update user set username=#{username},birthday=#{birthday},sex=#{sex},address=#{address} where id=#{id}")
    @ResultMap("userMap")
    void UpdateUser(User user);

    @Delete("delete from user where id=#{id}")
    void DeleteUserById(int id);

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where id=#{id}")
    User findById(int id);

    @Select("select * from user where username like #{username}")
    List<User> findLikeUsername(String username);

    /***
     * 聚合函数
     */
    @Select("select count(*) from user")
    int CountUsers();
}
