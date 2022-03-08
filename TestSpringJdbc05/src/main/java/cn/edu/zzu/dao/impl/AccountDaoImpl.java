package cn.edu.zzu.dao.impl;

import cn.edu.zzu.dao.IAccountDao;
import cn.edu.zzu.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDaoImpl implements IAccountDao {

    //使用spring的jdbctemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Account> findAll() {
        return jdbcTemplate.query("select * from account" , new BeanPropertyRowMapper<Account>(Account.class));
    }

    @Override
    public Account findByName(String name) {
        List<Account> query = jdbcTemplate.query("select * from account where name = ? ", new BeanPropertyRowMapper<Account>(Account.class) ,name);
        if (query.size()>1 || query.size()==0){
            throw new RuntimeException("账户不唯一！");
        }
        return query.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        int update = jdbcTemplate.update("update account set name = ? , money = ? where id = ?", account.getName(), account.getMoney(), account.getId());
        if (update != 1){
            throw new RuntimeException("更新账户异常！");
        }
    }
}
