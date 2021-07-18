package cn.edu.zzu.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接的工具类，它用于从数据源中获取一个连接，并且实现和线程的绑定
 */
@Component
public class ConnectionUtils {
    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    @Autowired
    private DataSource dataSource;

    /**
     * 获取当前线程上的连接
     * @return
     */
    public Connection getThreadConnection(){
        try{
            //先冲ThreadLocal上获取
            Connection connection = tl.get();
            //判断是否存在连接
            if(connection==null){
                //从数据源获取一个连接，并存入ThreadLocal
               connection = dataSource.getConnection();
               tl.set(connection);
            }
            return connection;
        }catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
    /**
     * 线程和连接解绑
     */
    public void removeConnection(){
        tl.remove();
    }
}
