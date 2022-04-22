package top.year21.web;


import com.google.gson.Gson;
import top.year21.bean.User;
import top.year21.service.UserService;
import top.year21.service.impl.UserServiceImpl;
import top.year21.utils.WebUtils;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


/**
 * @author hcxs1986
 * @version 1.0
 * @description: 只负责具体的程序业务逻辑，请求完成由父类Servlet程序处理
 * @date 2022/3/26 21:37
 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    /**
     * Description : 处理登录的业务
     * @date 2022/3/26 
     * @time 21:53
     * @user hcxs1986
     * @param req 
     * @param resp 
     * @return void
     **/
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //2.调用业务层的方法验证数据库是否存在当前用户名和密码
        User loginUser = userService.login(new User(null, username, password, null));
        if ( loginUser == null){

            //把错误信息和回显的数据保存在request域中
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",username);

            //为空证明用户不存在，跳回登录界面
            System.out.println("用户名或密码错误");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            //不为空证明用户名存在，跳转至登录成功的界面
            //保存用户登录成功后的信息到session域中
            req.getSession().setAttribute("user",loginUser);

            System.out.println("登录成功");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);

        }
    }

    /**
     * Description : 处理注册业务的功能
     * @date 2022/3/26
     * @time 21:54
     * @user hcxs1986
     * @param req
     * @param resp
     * @return void
     **/
    public void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String repwd = req.getParameter("repwd");
        String email = req.getParameter("email");


        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());


        //2.检查验证码是否正确
        //获取服务器生成的验证码信息
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //清空服务器生成的验证码信息
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //获取表单项的验证码信息
        String code = req.getParameter("code");
        if (token != null && token.equalsIgnoreCase(code)){
            //正确
            //3.检查用户名是否可用
            if (userService.isExistsUserName(username)){

//                System.out.println("用户名" + username + "已存在");

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
    /**
     * Description : 使用jquery的ajax发起异步请求判断用户名是否存在
     * @date 2022/4/1
     * @time 19:10
     * @user hcxs1986
     * @param req
     * @param resp
     * @return void
     **/
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setContentType("application/json");
        //1.获取用户名
        String username = req.getParameter("username");
        //2.调用UserService接口的实现类对象判断用户是否存在
        boolean result = userService.isExistsUserName(username);

        //3.将结果封装成map集合对象
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("result",result);

        //4.将map集合对象转换为json对象返回客户端
        Gson gson = new Gson();
        String jsonObj = gson.toJson(resultMap);

        resp.getWriter().write(jsonObj);

    }

    /**
     * Description : 处理登录后的注销业务
     * @date 2022/3/29
     * @time 17:30
     * @user hcxs1986
     * @param req
     * @param resp
     * @return void
     **/
    public void logOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.让保存用户信息的session失效
        HttpSession session = req.getSession();
        session.invalidate();

        //2.重定向至首页
        resp.sendRedirect(  req.getContextPath());
    }

}
