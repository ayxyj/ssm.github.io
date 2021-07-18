package cn.edu.zzu.service;

import cn.edu.zzu.domain.Account;

import java.util.List;

public interface IAccountService {
    /**
     * 查询所有用户
     * @return
     */
    List<Account> findAll();
}
