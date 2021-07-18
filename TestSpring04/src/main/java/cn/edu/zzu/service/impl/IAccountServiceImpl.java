package cn.edu.zzu.service.impl;

import cn.edu.zzu.service.IAccountService;

public class IAccountServiceImpl implements IAccountService {
    @Override
    public void saveAccount() {
        System.out.println("AccountService调用了saveAccount！");
    }
    public void init(){
        System.out.println("对象被创建！");
    }
    public void destory(){
        System.out.println("对象被销毁！");
    }
}
