package cn.edu.zzu.jdbc;

import cn.edu.zzu.entity.Items;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TestJDBC {
    public static void main(String[] args) {
        //Operate();
        //deleteById(1);
        showTabls();
    }

    public static void Operate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new java.util.Date()));// new Date()为获取当前系统时间  //2021-04-16 16:46:39

        System.out.println(new java.util.Date());//Fri Apr 16 16:46:39 CST 2021

        Date date = new Date(2021-1900,4-1,16);
        System.out.println(date);  //2021-04-16


        java.util.Date date1 = new java.util.Date();          // 获取一个Date对象
        Timestamp timeStamp = new Timestamp(date1.getTime());     //   讲日期时间转换为数据库中的timestamp类型  //2021-04-16 16:46:39.602

        System.out.println(timeStamp);
        Items items1 = new Items(1,"你好1",100,"path",timeStamp,"插入");
        Items items2 = new Items(2,"你好2",100,"path",timeStamp,"插入");
        Items items3 = new Items(3,"你好3",100,"path",timeStamp,"插入");
        insertItems(items1);
        insertItems(items2);
        insertItems(items3);
        findById(2);
    }
    public static void insertItems(Items items){
        Connection conn = null ;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql:///maven_0416?characterEncoding=UTF-8";
            conn = DriverManager.getConnection(url, "root", "root");
            String sql = "insert into items(name,price,pic,createtime,detail) values (?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            //设置占位符参数
            //pst.setInt(1,items.getId());
            pst.setString(1,items.getName());
            pst.setInt(2,items.getPrice());
            pst.setString(3,items.getPic());
//            pst.setDate(4, (Date) items.getCreatetime());
            pst.setTimestamp(4, (Timestamp) items.getCreatetime());
            pst.setString(5,items.getDetail());
            //执行
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
                try {
                    pst.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    try {
                        conn.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
        }
    }
    public static void findById(int id ){
        ResultSet rs = null ;
        PreparedStatement pst = null;
        Connection connection = null;
        try {
            //加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/maven_0416", "root", "root");
            //获取预处理对象
            String sql="select * from items where id = ?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            //执行sql
            rs = pst.executeQuery();
            //遍历结果集
            while(rs.next()){
                //封装实体
                Items items = new Items();
                items.setId(rs.getInt("id"));
                items.setName(rs.getString("name"));
                items.setPrice(rs.getInt("price"));
                items.setPic(rs.getString("pic"));
                items.setCreatetime(rs.getDate("createtime"));
                items.setDetail(rs.getString("detail"));
                System.out.println(items.toString());
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(rs!=null){
                    rs.close();
                }
                if(pst!=null){
                    pst.close();
                }
                if(connection!=null){
                    connection.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //删除
    public static void deleteById( int id ) {

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql:///maven_0416?characterEncoding=UTF-8";
            String username = "root";
            String password = "root";
            conn = DriverManager.getConnection(url,username,password);
            String sql = "delete from items where id = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,1);
            pst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(rs!=null){
                    rs.close();
                }
                if(pst!=null){
                    pst.close();
                }
                if(conn!=null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


/*    public List<String> listAllTables() {
        Connection conn = getConnection();
        if(conn == null){
            return null;
        }

        List<String> result = new ArrayList<>();
        ResultSet rs = null;
        try{
            //参数1 int resultSetType
            //ResultSet.TYPE_FORWORD_ONLY 结果集的游标只能向下滚动。
            //ResultSet.TYPE_SCROLL_INSENSITIVE 结果集的游标可以上下移动，当数据库变化时，当前结果集不变。
            //ResultSet.TYPE_SCROLL_SENSITIVE 返回可滚动的结果集，当数据库变化时，当前结果集同步改变。

            //参数2 int resultSetConcurrency
            //ResultSet.CONCUR_READ_ONLY 不能用结果集更新数据库中的表。
            //ResultSet.CONCUR_UPDATETABLE 能用结果集更新数据库中的表

            conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            DatabaseMetaData meta = conn.getMetaData();
            //目录名称; 数据库名; 表名称; 表类型;
            rs = meta.getTables(catalog(), schemaPattern(), tableNamePattern(), types());
            while(rs.next()){
                result.add(rs.getString("TABLE_NAME"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            close(conn, null, rs);
        }

        return result;
    }*/


    //获取所有数据库表  metadata信息
    public static void showTabls(){
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            DatabaseMetaData meta = conn.getMetaData();
            //目录名称; 数据库名; 表名称; 表类型;
            rs = meta.getTables(null, null, null , types());
            while(rs.next()){
                System.out.println(rs.getString("TABLE_NAME"));
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    //获取数据库连接
    public static  Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql:///maven_0416?characterEncoding=UTF-8";
            String username = "root";
            String password = "root";

            conn = DriverManager.getConnection(url, username, password);
        }catch (Exception e ){
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭(释放)资源
     * @param conn Connection
     * @param ps PreparedStatement
     * @param rs ResultSet
     */
    protected void close(Connection conn, Statement ps, ResultSet rs){
        //关闭ResultSet
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                rs = null;
            }
        }
        //关闭PreparedStatement
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                ps = null;
            }
        }
        //关闭Connection
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                conn = null;
            }
        }
    }



    /**
     * a catalog name; must match the catalog name as it is stored in the database; "" retrieves those without a catalog; null means that the catalog name should not be used to narrow the search
     */
    protected String catalog(){
        return null;
    }

    /**
     * a table name pattern; must match the table name as it is stored in the database
     */
    protected String tableNamePattern(){
        return "%";
    }

    /**
     * a list of table types, which must be from the list of table types returned from {@link //DatabaseMetaData.getTableTypes},to include; null returns all types
     */
    protected static String[] types(){

        return new String[]{"TABLE", "VIEW"};
    }

    /**
     * 加载驱动class
     * @return class
     */
   // protected abstract String loadDriverClass();

    /**
     * a schema name pattern; must match the schema name as it is stored in the database; "" retrieves those without a schema; null means that the schema name should not be used to narrow the search
     */
  //  protected abstract String schemaPattern();


}
