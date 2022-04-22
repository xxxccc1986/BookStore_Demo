package top.year21.service;

import top.year21.bean.User;

public interface UserService {

    /**
     * Description : 注册的业务
     * @date 2022/3/26
     * @time 22:52
     * @user hcxs1986
     * @param user
     * @return void
     **/
    public void registUser(User user);


    /**
     * Description : 登录的业务
     * @date 2022/3/26
     * @time 22:52
     * @user hcxs1986
     * @param user
     * @return top.year21.bean.User
     **/
    public User login(User user);

    /**
     * Description : 判断用户名是否可用
     * @date 2022/3/26
     * @time 22:52
     * @user hcxs1986
     * @param username
     * @return boolean
     **/
    public boolean isExistsUserName(String username);
}
