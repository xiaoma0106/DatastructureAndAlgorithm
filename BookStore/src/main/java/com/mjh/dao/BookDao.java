package com.mjh.dao;

import com.mjh.pojo.entity.domain.bean.Book;

import java.util.List;

/**
 * @author mjh
 * @date 2021-10-03 19:43
 */
public interface BookDao {
    public int addBook(Book book);
    public int deleteBookById(Integer id);
    public int updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();
    public Integer queryForPageTotalCount();
    public List<Book> queryForItems(int begin,int pageSize);
    public Integer queryForPageTotalCount(int min,int max);
    public List<Book> queryForItems(int begin,int pageSize,int min,int max);

}
