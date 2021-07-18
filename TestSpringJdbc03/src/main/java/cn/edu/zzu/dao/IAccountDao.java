package cn.edu.zzu.dao;

import cn.edu.zzu.doamin.Account;

import java.util.List;

public interface IAccountDao {
    /**
     *
     * @return
     */
    List<Account> findAll();

    /**
     * 根据名称查询
     * @param sourceName
     */
    Account findAccountByName(String sourceName);

    /**
     * 更新用户
     * @param source
     */
    void UpdateAccount(Account source);
}
