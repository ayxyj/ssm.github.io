package cn.edu.zzu.service.impl;

import cn.edu.zzu.dao.IAccountDao;
import cn.edu.zzu.domain.Account;
import cn.edu.zzu.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountDao ao;

    @Override
    public List<Account> findAllAccount() {
        return ao.findAll();
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return ao.findAccountById(accountId);
    }

    @Override
    public void saveAccount(Account account) {
        ao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        ao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer acccountId) {
        ao.deleteAccount(acccountId);
    }
}
