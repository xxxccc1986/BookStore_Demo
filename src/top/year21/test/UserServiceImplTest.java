package top.year21.test;

import org.junit.Test;
import top.year21.bean.User;
import top.year21.service.impl.UserServiceImpl;


public class UserServiceImplTest {
        UserServiceImpl user = new UserServiceImpl();
    @Test
    public void registUser() {
        user.registUser(new User(1,"测试人物","1235","147@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(user.login(new User(null, "测试人物", "1235", "147@qq.com")));
    }

    @Test
    public void isExistsUserName() {
        if (user.isExistsUserName("test")){
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名可用");
        }

    }
}