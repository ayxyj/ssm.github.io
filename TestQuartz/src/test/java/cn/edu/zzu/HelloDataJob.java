package cn.edu.zzu;

import cn.edu.zzu.service.HelloSpringService;
import cn.edu.zzu.utils.DFUtils;
import cn.edu.zzu.utils.SpringContextUtils;
import org.quartz.*;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.StringJoiner;


public class HelloDataJob implements Job {

    HelloSpringService helloSpringService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        Object detail_key_11 = context.getMergedJobDataMap().get("detail_key_1");
        System.out.println(detail_key_11);

        Trigger trigger = context.getTrigger();
        JobDetail jobDetail = context.getJobDetail();
        helloSpringService = (HelloSpringService) SpringContextUtils.applicationContext.getBean(StringUtils.uncapitalize(HelloSpringService.class.getSimpleName()));
        System.out.println(helloSpringService);

        Object detail_key_1 = jobDetail.getJobDataMap().get("detail_key_1");
        Object trigger_key_1 = trigger.getJobDataMap().get("trigger_key_1");
        System.out.println(detail_key_1+"++++++++++"+trigger_key_1);

        StringJoiner add = new StringJoiner("")
                .add("=========helloJob")
                .add(DFUtils.format(new Date()))
                .add(Thread.currentThread().getName())
                .add(context.getTrigger().getKey().getName());
        System.out.println(add);
    }
}
