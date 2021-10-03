package com.mjh.service;

import com.mjh.pojo.entity.domain.bean.Book;

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
}
