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

/**
 * @author hcxs1986
 * @version 1.0
 * @description: TODO
 * @date 2022/3/28 17:54
 */
public class ClientBookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * Description : 处理分页功能
     * @date 2022/3/28
     * @time 17:58
     * @user hcxs1986
     * @param req 请求参数对象
     * @param resp 响应参数对象
     * @return void
     **/
    public void getPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取请求的page参数
        int pageNo = WebUtils.getParseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.getParseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //2.通过bookService接口实现类获取指定Page类对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        //设置前台的请求地址头
        page.setUrl("client/bookServlet?action=getPage");

        //3.将此对象保存到request域中
        req.setAttribute("page",page);

        //4.跳转至pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    /**
     * Description : 按照用户输入的价格显示分页数据
     * @date 2022/3/29
     * @time 17:23
     * @user hcxs1986
     * @param req 请求参数对象
     * @param resp 响应参数对象
     * @return void
     **/
    public void getPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的page参数
        int pageNo = WebUtils.getParseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.getParseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.getParseInt(req.getParameter("min"), 0);
        int max = WebUtils.getParseInt(req.getParameter("max"), Integer.MAX_VALUE);

        //2.通过bookService接口实现类获取指定Page类对象
        Page<Book> page = bookService.queryBookByPrice(pageNo, pageSize, min, max);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=getPrice");
        if (req.getParameter("min") != null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max") != null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        //设置前台的请求地址头
        page.setUrl(sb.toString());

        //3.将此对象保存到request域中
        req.setAttribute("page",page);

        //4.跳转至/pages/client/index.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

}
