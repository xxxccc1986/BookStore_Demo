package top.year21.web;


import top.year21.bean.Book;
import top.year21.bean.Page;
import top.year21.service.BookService;
import top.year21.service.impl.BookServiceImpl;
import top.year21.utils.WebUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 只负责具体的程序业务逻辑，请求完成由父类Servlet程序处理
 * @date 2022/3/27 16:01
 */
public class BookServlet extends BaseServlet {

        private BookService bookService = new BookServiceImpl();

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.把请求的参数封装到bean对象中
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());

        //2.通过bookService接口实现类修改指定图书
        bookService.UpdateBook(book);

        //3.重定向至/manager/bookServlet?action=getPage图书显示页面
        resp.sendRedirect( req.getContextPath() + "/manager/bookServlet?action=getPage&pageNo=" + req.getParameter("pageNo"));
//        resp.sendRedirect( req.getContextPath() + "/manager/bookServlet?action=queryList");

    }


    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数的id，图书的编号
        int id = WebUtils.getParseInt(req.getParameter("id"), 0);
        //2.通过bookService接口实现类删除指定图书
        bookService.deleteBook(id);

        //3.重定向至/manager/bookServlet?action=getPage图书显示页面
        resp.sendRedirect( req.getContextPath() + "/manager/bookServlet?action=getPage&pageNo=" + req.getParameter("pageNo"));
//        resp.sendRedirect( req.getContextPath() + "/manager/bookServlet?action=queryList");
    }


    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.getParseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;

        //1.获取表单的请求数据
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());

        //2.将请求数据封装到bean对象中
        bookService.addBook(book);

        //3.跳转回前面/manager/bookServlet?action=getPage图书显示页面
        //使用跳转会导致用户使用F5刷新会重复发起 add请求，造成多次添加
        //通过使用resp的重定向会产生两次请求，在用户使用F5刷新的时候只会执行最后的getPage查询的页面方法。不会多次添加，
        resp.sendRedirect(req.getContextPath() +  "/manager/bookServlet?action=getPage&pageNo=" + pageNo);
//        resp.sendRedirect(req.getContextPath() +  "/manager/bookServlet?action=queryList");
//        req.getRequestDispatcher("manager/bookServlet?action=queryList").forward(req,resp);
    }

    protected void queryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.通过bookService接口实现类查询全部图书
        List<Book> books = bookService.queryBook();

        //2.将查询结果保存到request域中
        req.setAttribute("books",books);

        //3.请求转发跳转到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    public void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1获取图书的编号id
        String id = req.getParameter("id");
        int i = WebUtils.getParseInt(id, 0);

        //2.通过bookService接口实现类查询指定的图书
        Book book = bookService.queryById(i);

        //3.将参数内容保存到request域中
        req.setAttribute("book", book);

        //4.跳转至编辑的pages/manager/book_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    public void getPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的page参数
        int pageNo = WebUtils.getParseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.getParseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //2.通过bookService接口实现类获取指定Page类对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        //设置后台的请求地址头
        page.setUrl("manager/bookServlet?action=getPage");

        //3.将此对象保存到request域中
        req.setAttribute("page",page);

        //4.跳转至pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

}
