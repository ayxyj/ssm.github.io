package cn.edu.zzu.jdbcTemplateDemo;

import cn.edu.zzu.domain.Account;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class jdbcTemplate {
    public static void main(String[] args) {
        //数据源
        /**
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql:///maven_0512?characterEncoding=utf-8");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("root");


        //创建jdbctemplate
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(driverManagerDataSource);
        //执行查询所有操作
        List<Account> query = jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<Account>(Account.class));
        List<Account> query1 = jdbcTemplate.query("select * from account", new AccountRowMapper());
        for (Account ac : query1){
            System.out.println(ac);
        }

        //查询一行一列
        Long aLong = jdbcTemplate.queryForObject("select count(*) from account where money > ?", Long.class, 500);
        System.out.println(aLong);
        //插入
        //jdbcTemplate.update("insert into account(name,money)values(?,?)","test0519",3333.0);
         */

        //获取容器
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean.xml");
        //获取对象
        JdbcTemplate jdbcTemplate = classPathXmlApplicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        //jdbcTemplate.execute("insert into account(name,money)values('test0519.2',2222.0)");
    }

}


/**
 * 自定义封装策略，使用方法跟dbutils差不多
 * */
class AccountRowMapper implements RowMapper{
    /**
     * 把结果集中的数据封装到Account中，然后由spring把每个Account加到集合中
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setName(rs.getString("name"));
        account.setMoney(rs.getDouble("money"));
        return account;
    }
}