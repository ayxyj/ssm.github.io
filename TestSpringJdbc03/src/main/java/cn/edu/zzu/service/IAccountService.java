package cn.edu.zzu.service;

import cn.edu.zzu.doamin.Account;

import java.util.List;

public interface IAccountService {
    /**
     * 查询所有账户
     * @return
     */
    List<Account> findAll();
    /**
     * 转账
     */
    void transfer(String sourceName, String targetName, int money);
}
