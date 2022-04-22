package top.year21.test;

import org.junit.Test;
import top.year21.bean.User;
import top.year21.dao.impl.UserDaoImpl;

/**
 * @description: TODO
 * @author hcxs1986
 * @date 2022/3/25 10:44
 * @version 1.0
 */
public class UserDaoImplTest {
    UserDaoImpl dao = new UserDaoImpl();

    @Test
    public void getUserByName() {
        User test = dao.getUserByName("test");
        if (test == null){
            System.out.println("用户名可用");
        }else {
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void insert() {
        dao.insert(new User(1, "张三", "000000", "zhangsan@qq.com"));
    }

    @Test
    public void getUser() {
        User test = dao.getUser("test", "123456");
        if (test == null){
            System.out.println("用户名或密码错误，登录失败");
        }else {
            System.out.println("登录成功");
        }
    }
}