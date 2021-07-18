package cn.edu.zzu.factory;

import cn.edu.zzu.service.IAccountService;
import cn.edu.zzu.service.impl.IAccountServiceImpl;

/**
 * 模拟一个工厂类（该类可能是存在于jar包中的，我们无法通过修改源码方式来提供默认构造函数）
 */
public class InstanceFactory {
    public IAccountService getIAccountServiceImpl(){
        return new IAccountServiceImpl();
    }
    public static IAccountService getIAccountServiceImplStatic(){
        return new IAccountServiceImpl();
    }
}
