package cn.edu.zzu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 和事务相关的配置类
 */
public class TransactionConfig {

    /**
     * 事务管理器创建
     * @param dataSource
     * @return
     */
    @Bean(name="transactionManager")
    public DataSourceTransactionManager getTxManger(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
