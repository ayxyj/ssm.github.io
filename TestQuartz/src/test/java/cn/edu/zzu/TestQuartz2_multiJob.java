package cn.edu.zzu;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

public class TestQuartz2_multiJob {

    public static void main(String[] args) {

        try {

            /**
             *调度器：scheduler
             *触发器：Trigger
             *任务详情：JobDetail
             *任务：HelloJob
             */

            // 从Factory获取Scheduler实例
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // 开始
            scheduler.start();

            // define the job and tie it to our HelloJob class
            JobDetail job = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("job1", "group1")
                    .build();

            JobDetail job2 = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("job2", "group1")
                    .build();


            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(1)
                            .repeatForever())
                     .build();

            Trigger trigger2 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger2", "group1")
                    .forJob("job2" ,"group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(3)
                            .repeatForever())
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);
            scheduler.scheduleJob(job2 ,trigger2);

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
