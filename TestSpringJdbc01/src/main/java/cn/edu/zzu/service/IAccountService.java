package cn.edu.zzu.service;

import cn.edu.zzu.domain.Account;

import java.util.List;

public interface IAccountService {
    /**
     * 查询所有
     * @return
     */
    List<Account> findAll();

    /**
     * 转账
     */
    void transfer(String sourceName , String targetName , int money);
}
