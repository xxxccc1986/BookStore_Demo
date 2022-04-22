package top.year21.dao.impl;

import top.year21.bean.Book;
import top.year21.dao.BookDao;
import java.util.List;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: TODO
 * @date 2022/3/27 0:27
 */
public class BookDaoImpl extends DAO implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(name,price,author,sales,stock,img_path) " +
                     "values(?,?,?,?,?,?)";
        return utilsUpdate(sql,book.getName(),book.getPrice()
                ,book.getAuthor(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?";
        return utilsUpdate(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set name = ?,price = ?,author = ?,sales = ?,stock = ?,img_path = ? where id = ?";
       return utilsUpdate(sql,book.getName(),book.getPrice()
                ,book.getAuthor(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBook(Integer id) {
        String sql = "select id,name,price,author,sales,stock,img_path imgPath from t_book where id = ?";
        return utilsGetOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBookList() {
        String sql = "select id,name,price,author,sales,stock,img_path imgPath from t_book";
        return utilsGetList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
         Number count= (Number) utilsGetValue(sql);
        return count.intValue();
    }

    @Override
    public Integer queryForPageTotalCount(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number) utilsGetValue(sql,min,max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select id,name,price,author,sales,stock,img_path imgPath from t_book limit ?,?";
        return utilsGetList(Book.class,sql,begin,pageSize);
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize, int min, int max) {
        String sql = "select id,name,price,author,sales,stock,img_path imgPath from t_book where price between ? and ? order by price desc limit ?,? ";
        return utilsGetList(Book.class,sql,min,max,begin,pageSize);
    }
}
