package cn.edu.zzu.dao.impl;

import cn.edu.zzu.dao.IAccountDao;
import cn.edu.zzu.domain.Account;
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
    @Override
    public List<Account> findAll() {
        try {
            return runner.query("select * from account" , new BeanListHandler<Account>(Account.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

