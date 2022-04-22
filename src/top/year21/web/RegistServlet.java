package top.year21.web;


import top.year21.bean.User;
import top.year21.service.UserService;
import top.year21.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: TODO
 * @date 2022/3/24 19:13
 */
public class RegistServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String repwd = req.getParameter("repwd");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        //2.检查验证码是否正确,暂时要求验证码为abcde
        if ("abcde".equalsIgnoreCase(code)){
            //正确
            //3.检查用户名是否可用
            if (userService.isExistsUserName(username)){

                System.out.println("用户名" + username + "已存在");

                //不可用，把回显信息保存到request域中
                req.setAttribute("msg","用户名已存在！");
                req.setAttribute("username",username);
                req.setAttribute("email",email);

                //跳回注册登录界面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                //可用
                //调用service()保存到数据库
                userService.registUser(new User(null,username,password,email));
                //跳转到登录成功的界面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else {
            //不正确
            System.out.println("验证码" + code + "错误");

            //把回显信息保存到request域中
            req.setAttribute("msg","验证码错误！");
            req.setAttribute("username",username);
            req.setAttribute("email",email);

            //跳转到注册的界面
            RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/user/regist.jsp");
            dispatcher.forward(req,resp);

        }





    }
}
