package cn.edu.zzu.dao.impl;

import cn.edu.zzu.dao.IAccountDao;
import cn.edu.zzu.domain.Account;
import cn.edu.zzu.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
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
            return runner.query(connectionUtils.getThreadConnection() , "select * from account", new BeanListHandler<Account>(Account.class));
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public Account findAccountById(Integer accountId) {
        try {
            return runner.query(connectionUtils.getThreadConnection() , "select * from account where id = ?", new BeanHandler<Account>(Account.class) , accountId);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection() , "insert into account(name , money) values(?,?)", account.getName() , account.getMoney());
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection() , "update account set name=? , money=? where id = ?", account.getName() , account.getMoney(),account.getId());
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public void deleteAccount(Integer acccountId) {
        try {
            runner.update(connectionUtils.getThreadConnection() , "delete from account where id = ?", acccountId);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public List<Account> findAccountByName(String accountName) {
        try {
            return runner.query(connectionUtils.getThreadConnection() , "select * from account where name=?", new BeanListHandler<Account>(Account.class) , accountName);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public List<Account> findAccountByNameLike(String like) {
        try {
            return runner.query(connectionUtils.getThreadConnection() , "select * from account where name like ?", new BeanListHandler<Account>(Account.class) , like);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}
