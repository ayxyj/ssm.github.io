package cn.edu.zzu.service.impl;

import cn.edu.zzu.dao.IAccountDao;
import cn.edu.zzu.domain.Account;
import cn.edu.zzu.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS , readOnly = true)//只读型事务
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao ;
    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Account findByName(String name) {
        return accountDao.findByName(name);
    }

    @Override
    @Transactional(propagation =Propagation.REQUIRED , readOnly = false)//读写型事务配置
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    @Transactional(propagation =Propagation.REQUIRED , readOnly = false)//读写型事务配置
    public void tranfer(String sourceName, String targetName, int money) {
        System.out.println("transfer....");
        //2.1根据名称查询转出账户
        Account source = accountDao.findByName(sourceName);
        //2.2根据名称查询转入账户
        Account target = accountDao.findByName(targetName);
        //2.3转出账户减钱
        source.setMoney(source.getMoney()-money);
        //2.4转入账户加钱
        target.setMoney(target.getMoney()+money);
        //2.5更新转出账户
        accountDao.updateAccount(source);
        //int i=1/0;
        //2.6更新转入账户
        accountDao.updateAccount(target);
    }


}
