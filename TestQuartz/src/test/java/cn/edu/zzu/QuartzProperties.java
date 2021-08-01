package cn.edu.zzu;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class QuartzProperties {

    public static  void test(){
        Properties properties = new Properties();
        InputStream resourceAsStream = QuartzProperties.class.getClassLoader().getResourceAsStream("quartz.properties");
        try {
            properties.load(resourceAsStream);
            Enumeration<?> propertyNames = properties.propertyNames();
            while (propertyNames.hasMoreElements()){
                String strKey  =(String) propertyNames.nextElement();
                String strValue = properties.getProperty(strKey);
                System.out.println(strKey+":"+strValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            // 从Factory获取Scheduler实例
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // 开始
            scheduler.start();
            test();
            System.out.println("getSchedulerName:"+scheduler.getSchedulerName());
            System.out.println("线程数："+scheduler.getMetaData().getThreadPoolSize());

            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
