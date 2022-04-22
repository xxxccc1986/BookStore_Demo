package top.year21.service;

import top.year21.bean.Book;
import top.year21.bean.Page;
import java.util.List;

public interface BookService {

    /**
     * Description : 业务层修改书籍
     * @date 2022/3/27
     * @time 15:46
     * @user hcxs1986
     * @param book 实体类bean对象
     * @return void
     **/
    void UpdateBook(Book book);

    /**
     * Description : 业务层删除书籍
     * @date 2022/3/27
     * @time 15:46
     * @user hcxs1986
     * @param id 书籍id
     * @return void
     **/
    void deleteBook(Integer id);

    /**
     * Description : 业务层增加书籍
     * @date 2022/3/27
     * @time 15:47
     * @user hcxs1986
     * @param book 实体类bean对象
     * @return void
     **/
    void addBook(Book book);

    /**
     * Description : 业务层通过id查询书籍
     * @date 2022/3/27
     * @time 15:49
     * @user hcxs1986
     * @param id 书籍id
     * @return Book
     **/
    Book queryById(Integer id);


    /**
     * Description : 查询所有书籍
     * @date 2022/3/27
     * @time 15:49
     * @user hcxs1986
     * @return List<Book>
     **/
    List<Book> queryBook();


    /**
     * Description : 根据pageNo和pageSize返回一个page实体类的bean对象
     * @date 2022/3/28
     * @time 0:05
     * @user hcxs1986
     * @param pageNo 当前页码
     * @param pageSize 当前页显示的数量
     * @return top.year21.bean.Page<top.year21.bean.Book>
     **/
    Page<Book> page(int pageNo,int pageSize);


    Page<Book> queryBookByPrice(int pageNo, int pageSize, int min, int max);
}
