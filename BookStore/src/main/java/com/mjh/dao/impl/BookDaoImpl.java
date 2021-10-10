package com.mjh.dao.impl;

import com.mjh.dao.BookDao;
import com.mjh.pojo.entity.domain.bean.Book;

import java.util.List;

/**
 * @author mjh
 * @date 2021-10-03 19:46
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(name,price,author,sales,stock,imgPath) values(?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getPrice(), book.getAuthor(),  book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql="delete from t_book where id=?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql="update t_book set name=?,price=?,author=?,sales=?,stock=?,imgPath=? where id=?";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql="select * from t_book where id=?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql="select * from t_book";
        return queryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
       String sql="select count(*) from t_book";
      Number count=(Number) queryForSingleValue(sql);
      return count.intValue();
    }

    @Override
    public List<Book> queryForItems(int begin, int pageSize) {
        String sql="select * from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCount(int min, int max) {
       String sql="select count(*) from t_book where price between ? and ?";
       Number count= (Number) queryForSingleValue(sql,min,max);
       return count.intValue();
    }

    @Override
    public List<Book> queryForItems(int begin, int pageSize, int min, int max) {
        String sql="select * from t_book where price between ? and ? limit ?,?";
        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }
}
