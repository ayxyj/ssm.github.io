package cn.edu.zzu.dao.impl;

import cn.edu.zzu.config.ConnectionUtils;
import cn.edu.zzu.dao.IAccountDao;
import cn.edu.zzu.doamin.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class AccountDaoImpl implements IAccountDao {

    @Autowired
    private QueryRunner runner;

    @Autowired
    private ConnectionUtils connectionUtils;

    @Override
    public List<Account> findAll() {
        try {
            return runner.query(connectionUtils.getThreadConnection(), "select * from account", new BeanListHandler<Account>(Account.class));
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public Account findAccountByName(String sourceName) {
        Account account;
        try {
            List<Account> query = runner.query(connectionUtils.getThreadConnection(), "select * from account where name = ?", new BeanListHandler<Account>(Account.class), sourceName);
            if (query.size() > 1 || query.size() == 0) {
                throw new RuntimeException("名称不唯一！");
            }
            account = query.get(0);
            return account;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public void UpdateAccount(Account source) {
        try {
            runner.update(connectionUtils.getThreadConnection() ,"update account set name = ?, money = ? where id = ?" , source.getName(),source.getMoney(),source.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
