package cn.edu.zzu.service.impl;

import cn.edu.zzu.service.IAccountService;

import java.util.Date;

/**
 * 构造方法注入数据
 */
public class IAccountServiceImpl implements IAccountService {
    //如果是经常变化的数据，并不适用于注入的方式
    private String name;
    private Integer age;
    private Date birthday;

    public IAccountServiceImpl(String name,Integer age,Date birthday){
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void  saveAccount(){
        System.out.println("service中的saveAccount方法执行了。。。"+name+","+age+","+birthday);
    }


}
