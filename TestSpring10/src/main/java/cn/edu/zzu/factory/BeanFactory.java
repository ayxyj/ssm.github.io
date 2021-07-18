package cn.edu.zzu.factory;

import cn.edu.zzu.service.IAccountService;
import cn.edu.zzu.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建Service的代理对象的工厂
 * 造accountService
 */
@Component("ProxyAccountService")
public class BeanFactory {
    @Autowired
    private IAccountService accountServiceImpl ;

    @Autowired
    private TransactionManager txManager ;

    /**
     * 获取Service代理对象，对方法进行增强，事务能够进行控制，对比AccountServiceOLD和AccountService
     * @return
     */
    @Bean(name="accountServiceImpl_proxy")
    public IAccountService getAccountService(){
       return  (IAccountService) Proxy.newProxyInstance(accountServiceImpl.getClass().getClassLoader(), accountServiceImpl.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Object rtValue = null ;
                try{
                    //开启事务
                    txManager.beginTransaction();
                    //执行方法
                    rtValue = method.invoke(accountServiceImpl ,args);
                    //提交事务
                    txManager.commit();
                    //返回结果
                    return rtValue;
                }catch (Exception e){
                    //回滚事务
                    txManager.rollback();
                    throw new RuntimeException(e);
                }finally {
                    //释放连接
                    txManager.release();
                }

            }
        });
    }
}
