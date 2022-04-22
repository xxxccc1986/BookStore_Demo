package top.year21.test;

import org.junit.Test;
import top.year21.bean.Cart;
import top.year21.bean.CartItems;

import java.math.BigDecimal;


//此类是为了让购物车的商品更多样化 后将此类作为map集合中键值对的值加入到cart类中
public class CartTest {
    Cart cart = new Cart();
    @Test
    public void add() {
        cart.addItems(new CartItems(1,"test",1,new BigDecimal(10),new BigDecimal(10)));
        cart.addItems(new CartItems(1,"test",1,new BigDecimal(10),new BigDecimal(10)));
        cart.addItems(new CartItems(2,"testBook",1,new BigDecimal(10),new BigDecimal(10)));

        cart.deleteItems(1);

        cart.clear();

        cart.addItems(new CartItems(1,"test",1,new BigDecimal(10),new BigDecimal(10)));

        cart.updateCount(1,20);

        System.out.println(cart);

    }

    @Test
    public void delete() {
    }

    @Test
    public void updateCount() {

    }

    @Test
    public void clear() {
    }
}