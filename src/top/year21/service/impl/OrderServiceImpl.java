package top.year21.service.impl;

import top.year21.bean.*;
import top.year21.dao.BookDao;
import top.year21.dao.OrderDao;
import top.year21.dao.OrderItemDao;
import top.year21.dao.impl.BookDaoImpl;
import top.year21.dao.impl.OrderDaoImpl;
import top.year21.dao.impl.OrderItemDaoImpl;
import top.year21.service.OrderService;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: TODO
 * @date 2022/3/30 22:08
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    /**
     * Description : 生成订单
     * @date 2022/3/31
     * @time 11:29
     * @user hcxs1986
     * @param cart
     * @param userId
     * @return java.lang.String
     **/
    @Override
    public String createOrder(Cart cart, Integer userId) {

        System.out.println("OrderServiceImpl 当前使用的线程名是：" + Thread.currentThread().getName());

        //生成订单号
        String orderId = System.currentTimeMillis() + "" + userId;
        //创建一个订单号
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        orderDao.saveOrder(order);

        //遍历购物车中每一个商品项转换为订单项保存到数据库中
        for (Map.Entry<Integer, CartItems> entry : cart.getItems().entrySet() ){
            //获取每一个购物车中的订单项
            CartItems cartItems = entry.getValue();
            //转换为每一个订单项
            OrderItem orderItem = new OrderItem(null, cartItems.getName(), cartItems.getCount(), cartItems.getPrice(), cartItems.getTotalPrice(), orderId);
            //保存订单到数据库
            orderItemDao.saveOrderItem(orderItem);

            //根据购物车中图书的id查找到指定的图书
            Book book = bookDao.queryBook(cartItems.getId());
            //修改其销量值
            book.setSales(book.getSales() + cartItems.getCount());
            //修改其库存值
            book.setStock(book.getStock() - cartItems.getCount());

            //更新指定id的书籍信息
            bookDao.updateBook(book);

        }

        //在付款后清空购物车的商品
        cart.clear();

        //返回订单号
        return orderId;
    }

    /**
     * Description : 管理员查询所有的订单信息
     * @date 2022/3/31
     * @time 15:08
     * @user hcxs1986
     * @return java.util.List<top.year21.bean.Order>
     **/
    @Override
    public List<Order> showOrderByAdmin() {
        //调用orderDao的实现类查询所有书籍
        List<Order> orders = orderDao.showAllOrder();
        return orders;
    }

    /**
     * Description : 管理员根据指定id修改订单状态
     * @date 2022/3/31
     * @time 15:32
     * @user hcxs1986
     * @param orderId
     * @return void
     **/
    @Override
    public void sendOrder(String orderId) {
        //修改指定订单号状态
        //调用orderDao的实现类修改订单状态
        Integer status = 1;
        orderDao.changOrderStatu(orderId,status);
    }

    /**
     * Description : 根据指定订单号修改订单
     * @date 2022/3/31
     * @time 23:28
     * @user hcxs1986
     * @param orderId
     * @return top.year21.bean.OrderItem
     **/
    @Override
    public OrderItem showOrderByid(String orderId) {
        //根据指定的订单号查找指定的订单详情
        return  orderItemDao.showOrderByid(orderId);
    }

    /**
     * Description : 根据用户id查询有关的订单项
     * @date 2022/3/31
     * @time 23:28
     * @user hcxs1986
     * @param userId
     * @return java.util.List<top.year21.bean.Order>
     **/
    @Override
    public List<Order> showOrderByuserId(Integer userId) {
        //根据指定的用户id号查找相关的订单
        //调用orderDao的实现类查找相关的订单
       return orderDao.queryByuserId(userId);
    }

    /**
     * Description : 用户签收订单
     * @date 2022/3/31
     * @time 23:29
     * @user hcxs1986
     * @param orderId
     * @return void
     **/
    @Override
    public void receiverOrder(String orderId) {
        //根据指定的订单号查找指定的订单详情
        Integer status = 2;
        orderDao.receiverOrderById(orderId,status);
    }


}
