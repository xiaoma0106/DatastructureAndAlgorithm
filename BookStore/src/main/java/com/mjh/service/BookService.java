package com.mjh.service;

import com.mjh.pojo.entity.domain.bean.Book;
import com.mjh.pojo.entity.domain.bean.Page;

import java.util.List;

/**
 * @author mjh
 * @date 2021-10-03 20:21
 */
public interface BookService {
    public void addBook(Book book);
    public void deleteBookById(Integer id);
    public void updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();
    public Page<Book> page(Integer pageNo,Integer pageSize);

    public Page<Book> pageByPrice(Integer pageNo,Integer pageSize,Integer min,Integer max);
}
