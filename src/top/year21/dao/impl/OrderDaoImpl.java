package top.year21.dao.impl;

import top.year21.bean.Order;
import top.year21.dao.OrderDao;
import java.util.List;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: TODO
 * @date 2022/3/30 21:40
 */
public class OrderDaoImpl extends DAO implements OrderDao {
    @Override
    public int saveOrder(Order order) {

        System.out.println("OrderDaoImpl 当前使用的线程名是：" + Thread.currentThread().getName());

        String sql = "insert into t_order(order_id,creat_time,price,status,user_id) values(?,?,?,?,?)";
     return utilsUpdate(sql,order.getOrderId(),order.getCreatTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> showAllOrder() {
        String sql = "select order_id orderId,creat_time creatTime,price,STATUS status,user_id userId from t_order";
       return utilsGetList(Order.class,sql);
    }

    @Override
    public int changOrderStatu(String orderId, Integer status) {
        String sql = "update t_order set STATUS = ? where order_id = ?";
        return utilsUpdate(sql,status,orderId);
    }

    @Override
    public List<Order> queryByuserId(Integer userId) {
        String sql = "select order_id orderId,creat_time creatTime,price,STATUS status from t_order where user_id = ? ";
        return utilsGetList(Order.class,sql,userId);
    }

    @Override
    public int receiverOrderById(String orderId, Integer status) {
        String sql = "update t_order set STATUS = ? where order_id = ?";
        return  utilsUpdate(sql,status,orderId);
    }


}
