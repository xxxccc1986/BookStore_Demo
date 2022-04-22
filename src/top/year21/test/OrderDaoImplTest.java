package top.year21.test;

import org.junit.Test;
import top.year21.bean.Order;
import top.year21.dao.OrderDao;
import top.year21.dao.impl.OrderDaoImpl;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDaoImplTest {
    private OrderDao orderDao = new OrderDaoImpl();
    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("1122334455",new Date(),new BigDecimal(100),0,1));
    }

    @Test
    public void showAllOrder() {
        List<Order> orders = orderDao.showAllOrder();
        for(Order o : orders){
            System.out.println(o);
        }
    }

    @Test
    public void changOrderStatu() {
        orderDao.changOrderStatu("16487131661431",1);
    }

    @Test
    public void queryByuserId() {
        List<Order> orders = orderDao.queryByuserId(19);
        for(Order order : orders){
            System.out.println(order);
        }
    }

    @Test
    public void receiverOrderById() {
        orderDao.receiverOrderById("16486533416069",2);
    }
}