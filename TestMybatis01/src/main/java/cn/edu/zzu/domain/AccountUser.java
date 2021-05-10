package cn.edu.zzu.domain;

import java.io.Serializable;

public class AccountUser extends Account implements Serializable {
        //用于查询账户信息 并且能够查询用户名称和地址方式
        //当然，也可以直接查询account 和 user
    private String username ;
    private String address ;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return super.toString() + "AccountUser{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
