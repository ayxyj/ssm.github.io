package cn.edu.zzu.dao;

import cn.edu.zzu.domain.Account;

import java.util.List;

public interface IAccountDao {
    /**
     * 查询所有
     * @return
     */
    List<Account> findAll();

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    Account findByName(String name);

    /**
     * 更新用户
     * @param account
     */
    void updateAccount(Account account);
}
