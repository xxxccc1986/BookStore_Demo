package top.year21.test;

import org.junit.Test;
import top.year21.bean.Cart;
import top.year21.bean.CartItems;
import top.year21.bean.Order;
import top.year21.bean.OrderItem;
import top.year21.service.OrderService;
import top.year21.service.impl.OrderServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class OrderServiceImplTest {
    private OrderService orderService = new OrderServiceImpl();
    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItems(new CartItems(1,"test",1,new BigDecimal(10),new BigDecimal(10)));
        String order = orderService.createOrder(cart, 1);

        System.out.println("订单号是：" + order);
    }

    @Test
    public void sendOrder() {
        orderService.sendOrder("164865282429019");
    }

    @Test
    public void showOrderByid() {
        OrderItem orderItem = orderService.showOrderByid("164865282429019");
        System.out.println(orderItem);
    }

    @Test
    public void showOrderByuserId() {
        List<Order> orders = orderService.showOrderByuserId(19);
        for(Order order : orders){
            System.out.println(order);
        }
    }
}