package cn.edu.zzu.service.impl;

import cn.edu.zzu.dao.IAccountDao;
import cn.edu.zzu.domain.Account;
import cn.edu.zzu.service.IAccountService;
import cn.edu.zzu.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao ;
    @Autowired
    private TransactionManager txManager;

    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAll();
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
            accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer acccountId) {
        accountDao.deleteAccount(acccountId);
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
            List<Account> accountByName = accountDao.findAccountByName(sourceName);
            if (accountByName == null || accountByName.size() > 1) {
                throw new RuntimeException("转出账户错误！");
            }

            List<Account> accountByName1 = accountDao.findAccountByName(targetName);
            if (accountByName1 == null || accountByName1.size() > 1) {
                throw new RuntimeException("目标账户错误！");
            }
            Account AccountSource = accountByName.get(0);
            Account AccountTarget = accountByName1.get(0);

            AccountSource.setMoney(AccountSource.getMoney() - money);
            AccountTarget.setMoney(AccountTarget.getMoney() + money);


            accountDao.updateAccount(AccountSource);
//            int i = 1/0 ;//导致一致性问题 事务解决
            accountDao.updateAccount(AccountTarget);
    }
}
