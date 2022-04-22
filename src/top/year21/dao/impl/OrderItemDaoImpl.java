package top.year21.dao.impl;

import top.year21.bean.OrderItem;
import top.year21.dao.OrderItemDao;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: TODO
 * @date 2022/3/30 21:41
 */
public class OrderItemDaoImpl extends DAO implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {

        System.out.println("OrderItemDaoImpl 当前使用的线程名是：" + Thread.currentThread().getName());

        String sql = "insert into t_order_item(name,count,price,total_price,order_id) values(?,?,?,?,?)";
       return utilsUpdate(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public OrderItem showOrderByid(String orderId) {
        String sql = "select name ,count ,price ,total_price totalPrice,order_id orderId from t_order_item where order_id = ?";
        return utilsGetOne(OrderItem.class,sql,orderId);
    }
}
