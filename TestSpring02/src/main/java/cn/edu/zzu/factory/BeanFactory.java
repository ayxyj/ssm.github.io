package cn.edu.zzu.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BeanFactory {
    //定义一个Properties对象
    private static Properties props;

    private static Map<String,Object> beans;
    //使用静态代码块为Properties对象赋值
    static{
        //实例化
        props = new Properties();
        //获取文件流
        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("Bean.properties");
        try {
            props.load(resourceAsStream);
            //实例化容器
            beans = new  HashMap<String , Object>();
            //取出配置文件中的key
            Enumeration<Object> keys = props.keys();
            //遍历枚举
            while(keys.hasMoreElements()){
                //key
                String key = keys.nextElement().toString();
                //value
                String value = props.getProperty(key);
                //反射创建对象
                Object o = null;
                try {
                    o = Class.forName(value).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                //存储到map
                beans.put(key , o);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getBean(String beanName) {
        return beans.get(beanName);
    /*    Object bean = null;
        String beanPath = props.getProperty(beanName);
        try {
            bean = Class.forName(beanPath).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bean;*/
    }
}
