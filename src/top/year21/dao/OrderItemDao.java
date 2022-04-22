package top.year21.dao;

import top.year21.bean.OrderItem;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: TODO
 * @date 2022/3/30 21:34
 */
public interface OrderItemDao {
    int saveOrderItem(OrderItem orderItem);

    OrderItem showOrderByid(String orderId);

}
