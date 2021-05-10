package cn.edu.zzu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserJdbc {
    private static Connection conn= null;
    private static PreparedStatement pst = null;
    public static void main(String[] args) throws Exception{

        //类加载器
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接
        conn = DriverManager.getConnection("jdbc:mysql:///maven_0417?characterEncoding=UTF-8","root","root");
        //获取statement
        String sql = "insert into user(username,birthday,sex,address) values(?,?,?,?)";
        pst = conn.prepareStatement(sql);
        //设置参数
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String format = df.format(date);

        Timestamp tt = new Timestamp(date.getTime());
        pst.setString(1,"zzu_03");
        pst.setTimestamp(2,tt);
        pst.setString(3,"男");
        pst.setString(4,"河南");
        //执行
        pst.execute();
        //关闭
        Close();
    }
    public static void Close() throws Exception{
        conn.close();
        pst.close();
    }
}
