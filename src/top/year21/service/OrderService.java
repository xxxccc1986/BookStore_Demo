package top.year21.service;

import top.year21.bean.Cart;
import top.year21.bean.Order;
import top.year21.bean.OrderItem;

import java.util.List;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: TODO
 * @date 2022/3/30 22:07
 */
public interface OrderService {
    String createOrder(Cart cart,Integer userId);

    List<Order> showOrderByAdmin();

    void sendOrder(String orderId);

    OrderItem showOrderByid(String orderId);

    List<Order> showOrderByuserId(Integer userId);

    void receiverOrder(String orderId);
}
