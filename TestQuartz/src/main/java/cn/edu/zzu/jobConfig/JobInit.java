package cn.edu.zzu.jobConfig;

import cn.edu.zzu.job.HelloJob;
import cn.edu.zzu.job.SpringJob1;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * 注入到Spring容器中，容器启动时候创建bean，执行该方法@PostConstruct
 */
@Component
public class JobInit {

    Scheduler scheduler;

    {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Scheduler defaultScheduler = null;

        defaultScheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail job1 = JobBuilder.newJob(SpringJob1.class)
                .withIdentity("job1", "group1")
                .build();

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger1 = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .forJob("job1" ,"group1")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("* * *  * * ? *"))
                .build();
        defaultScheduler.start();

        defaultScheduler.scheduleJob(job1, trigger1);

        System.out.println("++++++++++___+++++++++");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        defaultScheduler.shutdown();

    }

    @PostConstruct
    public void initJob() {

        JobDetail job1 = JobBuilder.newJob(SpringJob1.class)
                .withIdentity("job1", "group1")
                .build();

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger1 = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .build();

        try {
            scheduler.start();
            scheduler.scheduleJob(job1, trigger1);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
