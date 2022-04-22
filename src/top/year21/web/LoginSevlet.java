package top.year21.web;

import top.year21.bean.User;
import top.year21.service.UserService;
import top.year21.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: TODO
 * @date 2022/3/24 21:55
 */
public class LoginSevlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //2.调用业务层的方法验证数据库是否存在当前用户名和密码
        if (userService.login(new User(null,username,password,null)) == null){

            //把错误信息和回显的数据保存在request域中
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",username);

            //为空证明用户不存在，跳回登录界面
            System.out.println("用户名或密码错误");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            //不为空证明用户名存在，跳转至登录成功的界面
            System.out.println("登录成功");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);

        }
    }
}
