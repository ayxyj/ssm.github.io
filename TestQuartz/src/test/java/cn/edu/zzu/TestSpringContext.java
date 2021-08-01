package cn.edu.zzu;

import cn.edu.zzu.config.SpringConfiguration;
import cn.edu.zzu.jobConfig.JobInit;
import cn.edu.zzu.service.HelloSpringService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class TestSpringContext {

    @Autowired
    HelloSpringService helloSpringService;

    @Autowired
    JobInit jobInit;
    @Test
    public void test(){
//        JonInit bean = (JonInit) SpringContextUtils.applicationContext.getBean(StringUtils.uncapitalize(JonInit.class.getSimpleName()));
        String test = helloSpringService.test();
        System.out.println("spring容器加载执行job  "+test);
    }
}
