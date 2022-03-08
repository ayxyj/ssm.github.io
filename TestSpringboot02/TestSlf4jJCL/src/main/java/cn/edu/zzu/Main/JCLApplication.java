package cn.edu.zzu.Main;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class JCLApplication {

    public static void main(String[] args) {
        Log log = LogFactory.getLog(JCLApplication.class);
        System.out.println(log.getClass());
        log.info("jcl 官方日志包");
    }
}
