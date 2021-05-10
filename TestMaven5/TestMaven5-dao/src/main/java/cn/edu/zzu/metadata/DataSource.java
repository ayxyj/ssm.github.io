package cn.edu.zzu.metadata;

import java.io.Serializable;

//数据源信息
public class DataSource implements Serializable {
    //数据库类型
    private JdbcType           jdbcType;
    //url
    private String            jdbcUrl;
    //user
    private String            jdbcUser;

    public JdbcType getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(JdbcType jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUser() {
        return jdbcUser;
    }

    public void setJdbcUser(String jdbcUser) {
        this.jdbcUser = jdbcUser;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getTvname() {
        return tvname;
    }

    public void setTvname(String tvname) {
        this.tvname = tvname;
    }

    //pwd
    private String            jdbcPassword;
    //表名 | 视图名
    private String            tvname;

    //getter 、setter...

}