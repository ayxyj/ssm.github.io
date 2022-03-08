package cn.edu.zzu.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.stereotype.Component;

/**
 * 1.全局配置mybatis的setting配置
 * 2.使用application.yml中的Configuration进行配置
 * 3.实现对应的接口去设置值
 * 仅需要使用一种方法去实现即可
 *
 * ag:下述是实现转驼峰命名规则的设置为true，当EmpMapper中没有定义ResultMap，要想实现属性字段和数据库表字段映射
 * 即下换线转驼峰命名可以通过设置MapUnderScoreToCamelCase去实现
 */
//@Component
public class MyConfigurationCustomizer implements ConfigurationCustomizer {
    @Override
    public void customize(Configuration configuration) {
        configuration.setMapUnderscoreToCamelCase(true);
    }
}
