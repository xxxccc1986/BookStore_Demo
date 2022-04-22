package top.year21.dao;

import top.year21.bean.Order;

import java.util.List;

public interface OrderDao {
    int saveOrder(Order order);

    List<Order> showAllOrder();

    int changOrderStatu(String orderId,Integer status);

    List<Order> queryByuserId(Integer userId);

    int receiverOrderById(String orderId,Integer status);
}
