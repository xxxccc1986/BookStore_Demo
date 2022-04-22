package top.year21.test;

import org.junit.Test;
import top.year21.bean.OrderItem;
import top.year21.dao.OrderItemDao;
import top.year21.dao.impl.OrderItemDaoImpl;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoImplTest {
    private OrderItemDao orderItem = new OrderItemDaoImpl();
    @Test
    public void saveOrderItem() {
        orderItem.saveOrderItem(new OrderItem(null,"test",1,new BigDecimal(100),new BigDecimal(200),"1122334455"));
    }


    @Test
    public void showOrderByid() {
        OrderItem orderItem = this.orderItem.showOrderByid("164865282429019");
        System.out.println(orderItem);
    }


}