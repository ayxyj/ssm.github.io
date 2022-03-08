package cn.edu.zzu.dao.impl;

import cn.edu.zzu.dao.IUserDao;
import cn.edu.zzu.domain.User;
import cn.edu.zzu.queryvo.QueryVo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IUserDaoImpl implements IUserDao {
    //释放资源
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @Override
    public List<User> findAll() {
        try {
            //加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql:///maven_0417", "root", "root");
            //获取prepareStatement
            String sql = "select * from user";
            pst = conn.prepareStatement(sql);
            //数据集
            rs = pst.executeQuery();
            //List
            List<User> list = new ArrayList<User>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setBirthday(rs.getDate("birthday"));
                user.setSex(rs.getString("sex"));
                user.setAddress(rs.getString("address"));
                list.add(user);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public int saveUser(User user) {
        return 0;
    }

    @Override
    public int deleteUserById(int id) {
        return 0;
    }

    @Override
    public int updateUserById(User user) {
        return 0;
    }

    @Override
    public List<User> findByLike(String like) {
        return null;
    }

    @Override
    public int CountUser() {
        return 0;
    }

    @Override
    public List<User> findByQueryVo(QueryVo vo) {
        return null;
    }

    @Override
    public List<User> findUserByCondition(User user) {
        return null;
    }

    @Override
    public List<User> findUserByQueryVoIds(QueryVo vo) {
        return null;
    }

    @Override
    public List<User> findUserAndAccounts() {
        return null;
    }

    @Override
    public List<User> findUserAndAccountsID(Integer id) {
        return null;
    }

}
