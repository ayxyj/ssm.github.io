package cn.edu.zzu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Account {
    public static void main(String[] args) throws Exception {
        //驱动注册
//        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        /**
         * 通过反射实现解耦合
         *
         *      当数据库包未导入时，第一种方式，在编译器就报错，而第二种方式在运行期才会报错，降低了程序之间的耦合性
         */
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///maven_0417?characterEncoding=UTF-8", "root", "root");
        //获取预处理对象
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println(resultSet.getString("username"));
        }
        connection.close();
    }
}
