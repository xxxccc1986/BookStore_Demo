package top.year21.test;

import top.year21.web.UserServlet;

import java.lang.reflect.Method;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: TODO
 * @date 2022/3/26 22:05
 */
public class UserServletTest {
    public void login(){
        System.out.println("这是登录的方法");
    }
    public void regist(){
        System.out.println("这是注册的方法");
    }
    public void updateUser(){
        System.out.println("这是修改用户的方法");
    }
    public void updateUserPassword(){
        System.out.println("这是修改密码的方法");
    }


    public static void main(String[] args){
        String action = "updateUser";

        try {
            //动态获取相应的字符串的方法
            Method dec = UserServletTest.class.getDeclaredMethod(action);

            //确保方法可访问
            dec.setAccessible(true);
            //调用目标的业务方法
            dec.invoke(new UserServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
