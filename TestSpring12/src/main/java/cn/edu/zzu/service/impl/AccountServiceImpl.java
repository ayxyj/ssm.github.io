package cn.edu.zzu.service.impl;

import cn.edu.zzu.service.IAccountService;
import org.springframework.stereotype.Repository;

@Repository
public class AccountServiceImpl implements IAccountService {
    @Override
    public void saveAccount() {
        System.out.println("执行了保存");
    }

    @Override
    public void updateAccount(int i) {
        System.out.println("执行了更新"+i);
    }

    @Override
    public int deleteAccount() {
        System.out.println("执行了删除");
        int i=1/0;
        return 0;
    }
}
