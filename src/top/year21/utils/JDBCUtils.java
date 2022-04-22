package top.year21.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 工具类
 * @date 2022/3/23 22:06
 */
public class JDBCUtils {

    //使用德鲁伊创建数据库连接池
    private static DataSource source;
    private static ThreadLocal<Connection> connect = new ThreadLocal<>();

    static {
        try {
            Properties pro = new Properties();
            InputStream resource = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(resource);
            source = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Description : 使用德鲁伊创建数据库连接池
     * @date 2022/3/25
     * @time 10:35
     * @user hcxs1986
     * @return java.sql.Connection
     **/
    public static Connection getConnection(){
        Connection con = connect.get();

        if (con == null){
            try {
                con = source.getConnection();//从数据连接池中获取连接

                connect.set(con);//保存到ThreadLocal对象中，供后面的JDBC操作使用

                con.setAutoCommit(false);//设置手动管理

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//        Connection connection = null;
//        try {
//            connection = source.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
        return con;
    }


//    //获取数据库连接
//    public static Connection getConnection(){
//        InputStream resource = null;
//        Connection connection = null;
//        try {
//            //1.提供连接的相关的信息
//            Properties pro = new Properties();
//            resource = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
//            pro.load(resource);
//
//            String username = pro.getProperty("username");
//            String password = pro.getProperty("password");
//            String url = pro.getProperty("url");
//            String driverClass = pro.getProperty("driverClass");
//
//            //2.加载驱动
//            Class.forName(driverClass);
//
//            //3.获取连接填写相关的信息
//            connection = DriverManager.getConnection(url, username, password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (resource != null)
//                    resource.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return connection;
//    }

    /**
     * Description : 提交事务，并关闭释放连接
     * @date 2022/3/31
     * @time 23:53
     * @user hcxs1986
     * @return void
     **/
    public static void commitAndClose(){
        Connection connection = connect.get();
        //不为空说明连接产生过，应该关闭
        if (connection != null){

            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();//关闭连接，释放资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        /*
         *下面这一步必须执行，不可缺少，否则报错(因为Tomcat服务器底层使用了线程池技术)
         */
        connect.remove();
    }

    /**
     * Description : 提交事务，并关闭释放连接
     * @date 2022/3/31
     * @time 23:54
     * @user hcxs1986
     * @return void
     **/
    public static void rollBack(){
        Connection connection = connect.get();
        //不为空说明连接产生过，应该关闭
        if (connection != null){

            try {
                connection.rollback();//回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();//关闭连接，释放资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        /*
         *下面这一步必须执行，不可缺少，否则报错(因为Tomcat服务器底层使用了线程池技术)
         */
        connect.remove();
    }

/*
    /**
     * Description : 针对通用增删改操作关闭资源
     * @date 2022/3/25
     * @time 10:34
     * @user hcxs1986
     * @param connection 数据库连接对象
     * @param ps 流
     * @return void
     *
    public static void closeRe(Connection connection, PreparedStatement ps){
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    */

/*
    /**
     * Description : 针对通用查询操作关闭资源
     * @date 2022/3/25
     * @time 10:35
     * @user hcxs1986
     * @param connection 数据库连接对象
     * @param ps 接口实例
     * @param rs 结果集对象
     * @return void
     *
    public static void SelectCloseRe(Connection connection, PreparedStatement ps, ResultSet rs){
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    */
}
