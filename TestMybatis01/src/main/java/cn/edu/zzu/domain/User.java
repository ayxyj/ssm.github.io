package cn.edu.zzu.domain;

import java.util.Date;
import java.util.List;

public class User {
    private Integer id ; //主键
    private String username ; //姓名
    private Date birthday ;  //生日
    private String sex ;    //性别
    private String address ; //地址

    //1:n
    private List<Account> accounts ;
    private List<Account> ids_accounts;

    public List<Account> getIds_accounts() {
        return ids_accounts;
    }

    public void setIds_accounts(List<Account> ids_accounts) {
        this.ids_accounts = ids_accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", accounts=" + accounts +
                ", ids_accounts=" + ids_accounts +
                '}';
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
