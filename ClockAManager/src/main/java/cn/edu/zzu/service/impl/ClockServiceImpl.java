package cn.edu.zzu.service.impl;

import cn.edu.zzu.job.ClockInJob;
import cn.edu.zzu.job.ResettingJob;
import cn.edu.zzu.job.SendMailJob;
import cn.edu.zzu.service.IClockService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClockServiceImpl implements IClockService {

    private  final Logger log = LoggerFactory.getLogger(ClockServiceImpl.class);
    private  Scheduler clockInScheduler ;
    private  Scheduler resettingScheduler ;
    private  Scheduler sendMailScheduler ;

    /**
     * 打卡操作
     * @throws SchedulerException
     */
    public  void clockInJob() throws SchedulerException {
        log.info("打卡-定时任务初始化");
        JobDetail clockInJobDetail = JobBuilder.newJob(ClockInJob.class).withIdentity("clockInJob","group1").build();

        // 创建一个Trigger实例
        Trigger clockInTrigger = TriggerBuilder.newTrigger()
                .withIdentity("clockInTrigger", "group1").startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/30 5,6 * * ? *"))
                .build();
        SchedulerFactory clockInSfact = new StdSchedulerFactory();
        clockInScheduler = clockInSfact.getScheduler();
        clockInScheduler.start();
        clockInScheduler.scheduleJob(clockInJobDetail, clockInTrigger);
        log.info("打卡-定时任务初始化完成");
    }

    /**
     * 重置打卡操作
     * @throws SchedulerException
     */
    public  void resettingJob() throws SchedulerException {
        log.info("重置-定时任务初始化");
        JobDetail resettingJobDetail = JobBuilder.newJob(ResettingJob.class).withIdentity("resettingJob","group2").build();

        // 创建一个Trigger实例
        Trigger resettingTrigger = TriggerBuilder.newTrigger()
                .withIdentity("resettingTrigger", "group2").startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0 30 10,14 * * ? *"))
                .build();

        SchedulerFactory resettingSfact = new StdSchedulerFactory();
        resettingScheduler = resettingSfact.getScheduler();

        resettingScheduler.start();
        resettingScheduler.scheduleJob(resettingJobDetail, resettingTrigger);
        log.info("重置-定时任务初始化完成");
    }

    /**
     * 发送邮件
     * @throws SchedulerException
     */
    public  void sendMail() throws SchedulerException {
        log.info("发送邮件-定时任务初始化");
        JobDetail sendMailJobDetail = JobBuilder.newJob(SendMailJob.class).withIdentity("sendMailJob","group3").build();
        // 创建一个Trigger实例
        Trigger sendMailTrigger = TriggerBuilder.newTrigger()
                .withIdentity("sendMailTrigger", "group3").startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 7 * * ? *"))
                .build();
        SchedulerFactory sendMailSfact = new StdSchedulerFactory();
        sendMailScheduler = sendMailSfact.getScheduler();
        sendMailScheduler.start();
        sendMailScheduler.scheduleJob(sendMailJobDetail, sendMailTrigger);
        log.info("发送邮件-定时任务初始化完成");
    }

    /**
     * 关闭定时任务
     */
    public void shutdownClockInScheduler(){
        try {
            clockInScheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭定时任务
     */
    public void shutdownResettingScheduler(){
        try {
            resettingScheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    /**
     * 关闭定时任务
     */
    public void shutdownRendMailScheduler(){
        try {
            sendMailScheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
