package cn.edu.zzu.service;

import org.quartz.SchedulerException;

public interface IClockService {
    void clockInJob() throws SchedulerException;
    void resettingJob() throws SchedulerException;
    void sendMail() throws SchedulerException;

    void shutdownRendMailScheduler();
    void shutdownResettingScheduler();
    void shutdownClockInScheduler();
}
