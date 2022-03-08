package cn.edu.zzu.dao.Impl;

import cn.edu.zzu.dao.UserInfoDao;
import cn.edu.zzu.domain.UserInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDaoImpl implements UserInfoDao {

    //connection
    Connection connection =null ;
    //获取操作数据对象
    PreparedStatement pst = null;
    //执行数据库操作
    ResultSet resultSet = null;
    @Override
    public List<UserInfo> findAll() throws Exception {
        List<UserInfo> list = new ArrayList<UserInfo>();
        try{
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //数据库connection
            connection = DriverManager.getConnection("jdbc:mysql:///personnel" ,"root","123456");
            //获取操作数据对象
            pst = connection.prepareCall("select  * from user_inf");
            //执行数据库操作
            resultSet = pst.executeQuery();
            //把数据库结果集转成java的List集合
            while (resultSet.next()){
                UserInfo user = new UserInfo();
                user.setId(resultSet.getInt("id"));
                user.setLoginName(resultSet.getString("loginname"));
                user.setPassword(resultSet.getString("password"));
                user.setStatus(resultSet.getInt("status"));
                user.setDate(resultSet.getString("createdate"));
                user.setUsername(resultSet.getString("username"));
                list.add(user);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            resultSet.close();
            pst.close();
            connection.close();
        }

        return list;
    }
}
