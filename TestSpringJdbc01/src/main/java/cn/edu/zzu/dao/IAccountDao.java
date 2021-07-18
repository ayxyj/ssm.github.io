package cn.edu.zzu.dao;

import cn.edu.zzu.domain.Account;

import java.util.List;

public interface IAccountDao {
    /**
     * 查询所有用户
     * @return
     */
    List<Account> findAccountAll();

    /**
     * 根据名称查询
     */
    Account findByName(String name);

    /**
     * 根据账户的id更新
     */
    void updateAccount(Account account);
}
