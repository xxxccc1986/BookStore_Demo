package top.year21.dao;

import top.year21.bean.Book;

import java.util.List;

public interface BookDao {

    /**
     * Description : 增加图书的方法
     * @date 2022/3/27
     * @time 0:33
     * @user hcxs1986
     * @param book book实体类的bean对象
     * @return int
     **/
    int addBook(Book book);


    /**
     * Description : 根据书籍的id删除信息
     * @date 2022/3/27
     * @time 0:37
     * @user hcxs1986
     * @param id 书籍的id
     * @return int
     **/
    int deleteBookById(Integer id);


    /**
     * Description : 更新对应书籍的信息
     * @date 2022/3/27
     * @time 0:37
     * @user hcxs1986
     * @param book book实体类的bean对象
     * @return int
     **/
    int updateBook(Book book);


    /**
     * Description : 根据书籍的id查询信息
     * @date 2022/3/27
     * @time 0:37
     * @user hcxs1986
     * @param id 书籍的id
     * @return top.year21.bean.Book
     **/
    Book queryBook(Integer id);


    /**
     * Description : 返回表中所有的数据
     * @date 2022/3/27
     * @time 15:39
     * @user hcxs1986
     * @return java.util.List<top.year21.bean.Book>
     **/
    List<Book> queryBookList();

    Integer queryForPageTotalCount();

    Integer queryForPageTotalCount(int min,int max);

    List<Book> queryForPageItems(int begin, int pageSize);

    List<Book> queryForPageItems(int begin, int pageSize,int min,int max);

}
