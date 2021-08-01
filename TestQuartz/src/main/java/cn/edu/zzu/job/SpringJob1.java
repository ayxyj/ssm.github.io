package cn.edu.zzu.job;

import cn.edu.zzu.utils.DFUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.StringJoiner;

public class SpringJob1 extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        StringJoiner stringJoiner = new StringJoiner("")
                .add("=========SpringJob1")
                .add(DFUtils.format(new Date()))
                .add(Thread.currentThread().getName())
                .add(context.getTrigger().getKey().getName());
        System.out.println(stringJoiner);
    }
}
