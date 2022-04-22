package top.year21.test;

import org.junit.Test;
import top.year21.bean.Book;
import top.year21.bean.Page;
import top.year21.service.BookService;
import top.year21.service.impl.BookServiceImpl;

import java.util.List;

import static org.junit.Assert.*;

public class BookServiceImplTest {
      private BookService book = new BookServiceImpl();
    @Test
    public void updateBook() {

    }

    @Test
    public void deleteBook() {
    }

    @Test
    public void addBook() {
    }

    @Test
    public void queryById() {
        Book book = this.book.queryById(112);
        System.out.println(book);
    }

    @Test
    public void queryBook() {
        List<Book> books = book.queryBook();
        for(Book b : books){
            System.out.println(b);
        }
    }

    @Test
    public void page() {
        System.out.println(book.page(1, Page.PAGE_SIZE) + "\t");

    }

    @Test
    public void queryBookByPrice() {
        System.out.println(book.queryBookByPrice(1, Page.PAGE_SIZE, 1, 200));

    }
}