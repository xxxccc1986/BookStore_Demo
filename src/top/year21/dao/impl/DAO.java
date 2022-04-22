package top.year21.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import top.year21.utils.JDBCUtils;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: TODO
 * @date 2022/3/24 15:36
 */
public abstract class DAO {

    private static QueryRunner runner = new QueryRunner();

    //使用utils工具
    //工具增删改
    public int utilsUpdate(String sql,Object ... args){

        Connection connection = JDBCUtils.getConnection();
        try {
           return runner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    //工具查询，返回一条记录
    public <T> T utilsGetOne(Class<T> clazz,String sql,Object... args){
        Connection connection = JDBCUtils.getConnection();
        try {
            return runner.query(connection,sql,new BeanHandler<T>(clazz),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //工具查询，返回多条记录
    public <T> List<T> utilsGetList(Class<T> clazz,String sql,Object... args){
        Connection connection = JDBCUtils.getConnection();
        try {
            return runner.query(connection,sql,new BeanListHandler<T>(clazz),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    //工具查询，返回特殊值查询记录
    public Object utilsGetValue(String sql,Object ... args){
        Connection connection = JDBCUtils.getConnection();
        try {
            ScalarHandler scalarHandler = new ScalarHandler();
            return runner.query(connection,sql,scalarHandler,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    /*
    自己手写的增删改查操作查询方法
     */
    //针对通用的增删改操作
    public int update(Connection connection,String sql,Object ...args){
        PreparedStatement ps = null;
        try {
            //1.预编译sql语句
            ps = connection.prepareStatement(sql);

            //2.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }

            //3.执行
            int update = ps.executeUpdate();
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        finally {
//            //4.关闭资源
//            JDBCUtils.closeRe(null,ps);
//        }
        return 0;
    }


    //针对某张表的通用查询操作,返回一条记录
    public  <E> E selectCheck(Class<E> clazz,Connection connection,String sql,Object ...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1.预编译sql语句
            ps = connection.prepareStatement(sql);

            //2.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }


            //3.执行并处理结果集
            rs = ps.executeQuery();
            ResultSetMetaData rsdm = rs.getMetaData();
            int columnCount = rsdm.getColumnCount();

            if (rs.next()){
                E e = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);

                    String columnLabel = rsdm.getColumnLabel(i + 1);

                    Field field = clazz.getDeclaredField(columnLabel);

                    field.setAccessible(true);

                    field.set(e,columnValue);
                }
                return e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            //4.关闭资源
//            JDBCUtils.SelectCloseRe(null,ps,rs);
//        }
        return null;
    }


    //针对不同表的通用查询操作，返回多条记录
    public  <E> List<E> selectListCheck(Class<E> clazz, Connection connection, String sql, Object ...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1.预编译sql语句
            ps = connection.prepareStatement(sql);

            //2.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }


            //3.执行并处理结果集
            rs = ps.executeQuery();
            ResultSetMetaData rsdm = rs.getMetaData();
            int columnCount = rsdm.getColumnCount();
            List<E> list = new ArrayList<>();
            while (rs.next()){
                E e = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);

                    String columnLabel = rsdm.getColumnLabel(i + 1);

                    Field field = clazz.getDeclaredField(columnLabel);

                    field.setAccessible(true);

                    field.set(e,columnValue);
                }
                list.add(e);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            //4.关闭资源
//            JDBCUtils.SelectCloseRe(null,ps,rs);
//        }
        return null;
    }

    //针对某个特殊值的查询，返回一条记录
    public <E> E getValue(Connection connection,String sql,Object ... args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }

            rs = ps.executeQuery();

            if (rs.next()){
                return (E)rs.getObject(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            JDBCUtils.SelectCloseRe(null,ps,rs);
//        }
        return null;
    }


}
