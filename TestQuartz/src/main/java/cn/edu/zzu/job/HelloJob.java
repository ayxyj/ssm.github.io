package cn.edu.zzu.job;

import cn.edu.zzu.utils.DFUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.StringJoiner;

public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        StringJoiner add = new StringJoiner("")
                .add("=========helloJob")
                .add(DFUtils.format(new Date()))
                .add(Thread.currentThread().getName())
                .add(context.getTrigger().getKey().getName());
        System.out.println(add);
    }
}
