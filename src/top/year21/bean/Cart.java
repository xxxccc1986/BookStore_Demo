package top.year21.bean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: TODO
 * @date 2022/3/29 21:42
 */
public class Cart {
    //这里其实前两个参数total、totalPrice都没用上，因为是相应参数的set方法去累加更新参数的值
    //之所以放在这是怕看不懂下方 所以保留下来
    private Integer total;
    private BigDecimal totalPrice;
    private Map<Integer,CartItems> items = new HashMap<>();

    /**
     * Description : 删除商品项
     * @date 2022/3/29
     * @time 21:51
     * @user hcxs1986
     * @param cartItem cartItem实体类bean对象
     * @return void
     **/
    public void addItems(CartItems cartItem){
        //判断此购物车内是否已经有此商品，若已有，则数量+1，总价更新，反之，将此商品添加进map集合中
        CartItems item = items.get(cartItem.getId());
        if (item == null){
            //没添加的清空
            items.put(cartItem.getId(),cartItem);
        }else {
            //已添加的情况
            //数量+1
            item.setCount(item.getCount() + 1);

            //更新总金额
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * Description : 删除指定id的商品
     * @date 2022/3/29
     * @time 21:52
     * @user hcxs1986
     * @param id 指定商品的id
     * @return void
     **/
    public void deleteItems(Integer id){
        items.remove(id);
    }


   /**
    * Description : 更新指定id商品的数量
    * @date 2022/3/29
    * @time 21:55
    * @user hcxs1986
    * @param id 指定商品的id
    * @param count 数量
    * @return void
    **/
    public void updateCount(Integer id,Integer count){
        //先查找是否有此商品,有则更新商品数量，更新总金额
        CartItems item = items.get(id);
        if (item != null){
            //更新商品数量
            item.setCount(count);
            //更新总金额
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }

    }

    /**
     * Description : 清空购物车
     * @date 2022/3/29
     * @time 21:54
     * @user hcxs1986
     * @return void
     **/
    public void clear(){
        items.clear();
    }

    public Cart() {
    }

    public Cart(Integer total, BigDecimal totalPrice, Map<Integer,CartItems> items) {
        this.total = total;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    //此方法可以用于累加更新参数的值
    public Integer getTotal() {

        total = 0;
        for (Map.Entry<Integer,CartItems> entry : items.entrySet()){
            total += entry.getValue().getCount();
        }
        return total;
    }

    //此方法可以用于累加更新参数的值
    public BigDecimal getTotalPrice() {
        totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer,CartItems> entry : items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer,CartItems> getItems() {
        return items;
    }

    public void setItems(Map<Integer,CartItems> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "total=" + getTotal() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
