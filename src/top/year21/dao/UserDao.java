package top.year21.dao;

import top.year21.bean.User;


public interface UserDao {


    /**
     * Description 根据用户名查询信息
     * @date 2022/3/25
     * @time 10:32
     * @user hcxs1986
     * @param name 用户输入的名字
     * @return top.year21.bean.User
     **/
    User getUserByName(String name);


    /**
     * Description : 保存用户到数据库
     * @date 2022/3/27
     * @time 0:29
     * @user hcxs1986
     * @param user 实体类bean对象
     * @return int
     **/
    int insert(User user);


    /**
     * Description : 根据用户名、密码查询查询信息
     * @date 2022/3/27
     * @time 0:30
     * @user hcxs1986
     * @param name 用户名
     * @param password 密码
     * @return top.year21.bean.User
     **/
    User getUser(String name,String password);

}
