package cn.edu.zzu.dao;

import cn.edu.zzu.domain.Account;

import java.util.List;

public interface IAccountDao {
    List<Account> findAll();
}
