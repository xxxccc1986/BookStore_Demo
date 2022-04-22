package top.year21.web;

import top.year21.bean.Cart;
import top.year21.bean.Order;
import top.year21.bean.OrderItem;
import top.year21.bean.User;
import top.year21.service.OrderService;
import top.year21.service.impl.OrderServiceImpl;
import top.year21.utils.JDBCUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 订单模块
 * @date 2022/3/30 22:38
 */
public class OrderServlet extends BaseServlet{
        private OrderService orderService = new OrderServiceImpl();
    /**
     * Description : 生成订单的业务
     * @date 2022/3/30
     * @time 22:39
     * @user hcxs1986
     * @param req
     * @param resp
     * @return void
     **/
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取购物车对象
       Cart cart = (Cart) req.getSession().getAttribute("cart");
       //获取userId
        User user = (User) req.getSession().getAttribute("user");

        if (user == null){
            req.getRequestDispatcher("pages/user/login.jsp").forward(req,resp);
            return;
        }

        System.out.println("OrderServlet 当前使用的线程名是：" + Thread.currentThread().getName());

        Integer id = user.getId();

       //2.调用orderService接口实现类对象方法生成订单号
        String orderId =  orderService.createOrder(cart, id);

        //3.把订单号存在session域中
        req.getSession().setAttribute("orderId",orderId);

        //4.重定向至pages/cart/checkout.jsp
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
//        请求转发不可用 存在重复提交表单的缺陷
//        req.getRequestDispatcher("pages/cart/checkout.jsp").forward(req,resp);
    }

    /**
     * Description : 返回所有订单项
     * @date 2022/3/31
     * @time 11:34
     * @user hcxs1986
     * @return java.util.List<top.year21.bean.Order>
     **/
    protected void showAllOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //调用调用orderService接口实现类对象方法查看所有订单
        List<Order> allOrder = orderService.showOrderByAdmin();

        //把allOrder数据保存到request域中
        req.setAttribute("allOrder",allOrder);

        //请求转发至pages/manager/order_manager.jsp
        req.getRequestDispatcher("pages/manager/order_manager.jsp").forward(req,resp);

    }

    /**
     * Description : 管理员后台发货
     * @date 2022/3/31
     * @time 14:52
     * @user hcxs1986
     * @param req
     * @param resp
     * @return void
     **/
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取订单项的orderId
        String orderId = req.getParameter("orderId");
        //2.调用调用orderService接口实现类对象修改指定orderId的状态
        orderService.sendOrder(orderId);

        //3.跳转回原网页
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * Description : 查看订单详情
     * @date 2022/3/31
     * @time 17:19
     * @user hcxs1986
     * @param req
     * @param resp
     * @return void
     **/
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取订单号
        String orderId = req.getParameter("orderId");

        //2.调用调用orderService接口实现类对象查看指定id的订单号
        OrderItem orderItem = orderService.showOrderByid(orderId);

        //将此对象保存到request域中
        req.setAttribute("orderItem",orderItem);

        //跳转至pages/order/orderDetail.jsp
        req.getRequestDispatcher("pages/order/orderDetail.jsp").forward(req,resp);
    }


    /**
     * Description : 根据用户id显示订单
     * @date 2022/3/31
     * @time 17:51
     * @user hcxs1986
     * @param req
     * @param resp
     * @return void
     **/
    protected void showMyOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取用户的id
        User user = (User) req.getSession().getAttribute("user");
        Integer userId = user.getId();

        //2.调用orderService接口实现类对象查看指定用户id的订单号
        List<Order> orders = orderService.showOrderByuserId(userId);

        //将此集合存入request域中
        req.setAttribute("orders",orders);

        //跳转至pages/order/order.jsp
        req.getRequestDispatcher("pages/order/order.jsp").forward(req,resp);
    }


    /**
     * Description : 用户签收订单业务
     * @date 2022/3/31
     * @time 23:26
     * @user hcxs1986
     * @param req
     * @param resp
     * @return void
     **/
    protected void receiver(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求签收的订单项
        String orderId = req.getParameter("orderId");

        //2.调用orderService接口实现类对象修改指定订单的状态
        orderService.receiverOrder(orderId);

        //3.重定向至原网页
        resp.sendRedirect(req.getHeader("Referer"));

    }
}
