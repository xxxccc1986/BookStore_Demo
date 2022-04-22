package top.year21.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 作为Servlet程序的基础Servlet
 * 用途当继承的servlet程序收到提交的post请求就会执行此程序的doPost方法
 * 利用反射动态获取对应隐藏域的name属性值相应的getXX方法，执行相应的方法体
 * @date 2022/3/26 22:33
 */
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("action");

        try {
            //动态获取相应的字符串的方法
            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);

            //确保方法可访问
            method.setAccessible(true);

            //调用目标的业务方法
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);//将所有异常抛出，交由Filter过滤器处理
        }
    }

}
