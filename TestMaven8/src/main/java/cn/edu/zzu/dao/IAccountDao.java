package cn.edu.zzu.dao;

import cn.edu.zzu.domain.Account;
import cn.edu.zzu.domain.AccountUser;

import java.util.List;

public interface IAccountDao {
    //查询所有信息
    List<Account> findAll();

    //查询用户名和地址并且还有account信息
    List<AccountUser> findAccountUser();

    //封装user 到 account
    List<Account> findAccountAndUser();
}
