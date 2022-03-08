package cn.edu.zzu.jobConfig;

import cn.edu.zzu.job.SpringJob1;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 定义Trigger JobDetail的bean，spring会自动关联到scheduler上
 */
@Configuration
public class JobConfig {

    @Bean
    public JobDetail JobDetail() {
        return JobBuilder.newJob(SpringJob1.class)
                .withIdentity("job2", "group2")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger TriggerBuilder() {
        return TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "group2")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .build();
    }
}
