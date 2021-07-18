package cn.edu.zzu.dao.impl;

import cn.edu.zzu.config.ConnectionUtils;
import cn.edu.zzu.dao.IAccountDao;
import cn.edu.zzu.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import java.sql.SQLException;
import java.util.List;

public class AccountDaoImpl implements IAccountDao {

    private QueryRunner runner;
    private ConnectionUtils connectionUtils;

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 查询所有用户，没有整合ssm,使用QueryRunner
     * @return
     */
    @Override
    public List<Account> findAccountAll() {
        System.out.println(runner);
        System.out.println(connectionUtils);
        try{
            return runner.query(connectionUtils.getThreadConnection(),"select * from account",new BeanListHandler<Account>(Account.class));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 根据名称查询
     * @param name
     * @return
     */
    @Override
    public Account findByName(String name) {
        Account account = null;
        try {
            account = runner.query(connectionUtils.getThreadConnection(), "select * from account where name = ?", new BeanHandler<Account>(Account.class), name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return account;
    }

    /**
     * 根据id更新账户
     * @param account
     */
    @Override
    public void updateAccount(Account account) {
        int update = 0;
        try {
            update = runner.update(connectionUtils.getThreadConnection(), "update account set name = ? , money =? where id =?", account.getName(), account.getMoney(), account.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

