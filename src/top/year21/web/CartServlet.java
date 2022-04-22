package top.year21.web;

import com.google.gson.Gson;
import top.year21.bean.Book;
import top.year21.bean.Cart;
import top.year21.bean.CartItems;
import top.year21.service.BookService;
import top.year21.service.impl.BookServiceImpl;
import top.year21.utils.WebUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: TODO
 * @date 2022/3/29 22:50
 */
public class CartServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    /**
     * Description : 加入购物车
     * @date 2022/3/29
     * @time 22:51
     * @user hcxs1986
     * @param req
     * @param resp
     * @return void
     **/
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取参数中的书籍id
        String id = req.getParameter("id");
        int bookId = WebUtils.getParseInt(id, 0);
        //2.通过bookService接口的实现类查找指定id的书籍
        Book book = bookService.queryById(bookId);

        //3.将指定的id书籍转化为CartItems对象
        CartItems cartItem = new CartItems(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());

        //4.调用addItem方法加入
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItems(cartItem);

//        System.out.println(cart);

        System.out.println(req.getHeader("Referer"));

        //将最后一个书籍的名字添加进session域中，便于返回首页后提供最后添加的最后一本书的名字
        req.getSession().setAttribute("lastName",cartItem.getName());

        //5.重定向回原来商品请求的页面
        resp.sendRedirect(req.getHeader("Referer"));

    }

    /**
     * Description : 使用ajax技术实现加入购物车的局部更新
     * @date 2022/4/1
     * @time 19:22
     * @user hcxs1986
     * @param req
     * @param resp
     * @return void
     **/
    protected void addItemByAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
//        resp.setContentType();
        resp.setContentType("text/html;charset=UTF-8");
        //1.获取参数中的书籍id
        String id = req.getParameter("bookId");
        int bookId = WebUtils.getParseInt(id, 0);
        //2.通过bookService接口的实现类查找指定id的书籍
        Book book = bookService.queryById(bookId);

        //3.将指定的id书籍转化为CartItems对象
        CartItems cartItem = new CartItems(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());

        //4.通过session取得购物车并调用addItem方法加入购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItems(cartItem);

        //返回购物车中的商品数量和最后一个添加的商品名称
        HashMap<String, Object> resultMap = new HashMap<>();

        resultMap.put("total",cart.getTotal());
        resultMap.put("lastname",cartItem.getName());

        //将集合对象转换为json对象
        Gson gson = new Gson();
        String resultMapJson = gson.toJson(resultMap);

        resp.getWriter().write(resultMapJson);
    }

    /**
     * Description : 删除指定id的书籍
     * @date 2022/3/30
     * @time 17:24
     * @user hcxs1986
     * @param req
     * @param resp
     * @return void
     **/
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取参数中的书籍id
        int id = WebUtils.getParseInt(req.getParameter("id"), 0);

        //2.调用cart类的相应方法删除指定id图书
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null){
            cart.deleteItems(id);
        }

        //3.重定向当前页
        resp.sendRedirect(req.getHeader("Referer"));
    }


    /**
     * Description : 清空购物车
     * @date 2022/3/30
     * @time 17:56
     * @user hcxs1986
     * @param req
     * @param resp
     * @return void
     **/
    protected void clearItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取购物车执行相应的clear方法
         Cart cart = (Cart) req.getSession().getAttribute("cart");

         if (cart != null){

             cart.clear();
         }

         //2.重定向回原网页
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * Description : 修改输入框内的书籍数量
     * @date 2022/3/30
     * @time 18:41
     * @user hcxs1986
     * @param req
     * @param resp
     * @return void
     **/
    protected void UpdateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数
        int count = WebUtils.getParseInt(req.getParameter("count"), 0);
        int bookId = WebUtils.getParseInt(req.getParameter("bookId"), 0);

        //2.获取购物车执行相应的updateCount方法
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null){
            cart.updateCount(bookId,count);
        }

        //3.重定向回原网页
        resp.sendRedirect(req.getHeader("Referer"));
    }


}
