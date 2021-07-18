package cn.edu.zzu.service.impl;

import cn.edu.zzu.dao.IAccountDao;
import cn.edu.zzu.doamin.Account;
import cn.edu.zzu.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }


    /**
     * 转账
     */
    @Override
    public void transfer(String sourceName, String targetName, int money){
        Account source = accountDao.findAccountByName(sourceName);
        Account target = accountDao.findAccountByName(targetName);
        source.setMoney(source.getMoney() - money);
        target.setMoney(target.getMoney() + money);
        accountDao.UpdateAccount(source);
        int i=1/0;
        accountDao.UpdateAccount(target);
    }
}
