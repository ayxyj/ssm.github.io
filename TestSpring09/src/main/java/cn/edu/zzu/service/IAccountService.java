package cn.edu.zzu.service;

import cn.edu.zzu.domain.Account;

import java.util.List;

public interface IAccountService {
    List<Account> findAll();
}
