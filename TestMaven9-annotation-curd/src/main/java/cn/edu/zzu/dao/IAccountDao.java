package cn.edu.zzu.dao;

import cn.edu.zzu.domain.Account;
import org.apache.ibatis.annotations.Select;

public interface IAccountDao {
    /**
     * 根据id查询用户
     * @param uid
     * @return
     */
    @Select("select * from account where uid = #{uid}")
    Account findById(int uid);
}
