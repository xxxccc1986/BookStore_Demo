package top.year21.test;

import org.junit.Test;
import top.year21.bean.Book;
import top.year21.bean.Page;
import top.year21.dao.impl.BookDaoImpl;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoImplTest {
    BookDaoImpl bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"人生迷茫路",new BigDecimal(99.99),"匿名",200,0,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(111);
        System.out.println("删除成功");
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(112,"人生迷茫路",new BigDecimal(100),"匿名",2000,0,null));
        System.out.println("修改成功");
    }

    @Test
    public void queryBook() {
        Book book = bookDao.queryBook(112);
        System.out.println(book);
    }

    @Test
    public void queryBookList() {
        List<Book> books = bookDao.queryBookList();
        for (Book book : books){
            System.out.println(book);
        }
    }


    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        bookDao.queryForPageItems(0, Page.PAGE_SIZE).forEach(System.out :: println);
    }

    @Test
    public void queryForPageTotalCount1() {
        System.out.println(bookDao.queryForPageTotalCount(1, 200));
    }

    @Test
    public void queryForPageItems1() {
        System.out.println(bookDao.queryForPageItems(1, Page.PAGE_SIZE, 1, 200));
    }

}