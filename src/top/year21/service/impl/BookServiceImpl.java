package top.year21.service.impl;

import top.year21.bean.Book;
import top.year21.bean.Page;
import top.year21.dao.BookDao;
import top.year21.dao.impl.BookDaoImpl;
import top.year21.service.BookService;
import java.util.List;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: TODO
 * @date 2022/3/27 15:39
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void UpdateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public Book queryById(Integer id) {
        return bookDao.queryBook(id);
    }

    @Override
    public List<Book> queryBook() {
       return bookDao.queryBookList();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> bookPage = new Page<>();

        //设置当前页显示的数据量
        bookPage.setPageSize(pageSize);

        //获取总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        //设置总记录数
        bookPage.setPageTotalCount(pageTotalCount);

        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        //此处表示不满4的页数算成以一页，不能用/号 不然只要大于0的结果都会被自增1
        if (pageTotalCount % pageSize > 0){
            pageTotal += 1;
        }

        //设置总页码
        bookPage.setPageTotal(pageTotal);

        //设置当前页码
        bookPage.setPageNo(pageNo);

        //求当前页的数据开始的索引
        int begin = (bookPage.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);

        //设置当前页数据
        bookPage.setItems(items);

        return bookPage;
    }

    @Override
    public Page<Book> queryBookByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> book = new Page<>();

        //设置当前页数据个数
        book.setPageSize(pageSize);

        //获取总记录数
        Integer pageTotalPriceCount = bookDao.queryForPageTotalCount(min, max);

        //设置总记录数
        book.setPageTotalCount(pageTotalPriceCount);

        //获取总页数
        int pageTotal = pageTotalPriceCount / pageSize;
        if (pageTotalPriceCount % pageSize > 0){
            pageTotal += 1;
        }

        //设置总页数
        book.setPageTotal(pageTotal);

        //设置当前页码
        book.setPageNo(pageNo);


        //获取当前页数据
        int begin = (book.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForPageItems(begin, pageSize, min, max);

        //设置当前页数据
        book.setItems(items);


        return book;
    }
}
