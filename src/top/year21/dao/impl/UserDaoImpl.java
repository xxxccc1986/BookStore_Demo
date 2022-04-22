package top.year21.dao.impl;

import top.year21.bean.User;
import top.year21.dao.UserDao;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: TODO
 * @date 2022/3/24 16:54
 */
public class UserDaoImpl extends DAO implements UserDao {
    @Override
    //根据用户名查询信息
    public User getUserByName(String name) {
        String sql = "select id,username,password,email from t_user where username = ?";
        return utilsGetOne(User.class, sql, name);
//        Connection connection = JDBCUtils.getConnection();
//        return selectCheck(User.class,connection,sql,name);
    }

    @Override
    //保存用户到数据库
    public int insert(User user) {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        return utilsUpdate(sql,user.getUsername(),user.getPassword(),user.getEmail());
//        Connection connection = JDBCUtils.getConnection();
//       return update(connection,sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

    @Override
    //根据用户名、密码查询查询信息
    public User getUser(String name, String password) {
        String sql = "select id,username,password,email from t_user where username = ? and password = ?";
        return utilsGetOne(User.class, sql, name,password);
    }
}
