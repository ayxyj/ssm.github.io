package cn.edu.zzu.dao.impl;

import cn.edu.zzu.dao.IAccountDao;
import cn.edu.zzu.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccounDaoImpl implements IAccountDao {

    //使用spring的jdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询所有账户
     *
     * @return
     */
    @Override
    public List<Account> findAll() {
        return jdbcTemplate.query("select * from account",new BeanPropertyRowMapper<Account>(Account.class));
    }
}
