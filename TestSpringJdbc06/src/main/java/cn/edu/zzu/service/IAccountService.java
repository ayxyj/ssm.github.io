package cn.edu.zzu.service;

import cn.edu.zzu.domain.Account;

import java.util.List;

public interface IAccountService {
    /**
     * 查询所有
     */
    List<Account> findAll();
    /**
     * 根据名称查询
     */
    Account findByName(String name);
    /**
     * 更新用户
     */
    void updateAccount(Account account);
    /**
     * 转账
     */
    void tranfer(String sourceName ,String targetName , int money);
 }
