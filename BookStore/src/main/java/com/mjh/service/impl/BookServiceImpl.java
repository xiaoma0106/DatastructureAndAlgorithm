package com.mjh.service.impl;

import com.mjh.dao.impl.BookDaoImpl;
import com.mjh.pojo.entity.domain.bean.Book;
import com.mjh.service.BookService;

import java.util.List;

/**
 * @author mjh
 * @date 2021-10-03 20:23
 */
public class BookServiceImpl implements BookService {
    private BookDaoImpl bookDao=new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }
}
