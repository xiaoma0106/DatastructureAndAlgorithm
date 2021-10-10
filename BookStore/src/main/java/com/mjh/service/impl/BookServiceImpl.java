package com.mjh.service.impl;

import com.mjh.dao.impl.BookDaoImpl;
import com.mjh.pojo.entity.domain.bean.Book;
import com.mjh.pojo.entity.domain.bean.Page;
import com.mjh.service.BookService;

import java.util.List;

/**
 * @author mjh
 * @date 2021-10-03 20:23
 */
public class BookServiceImpl implements BookService {
    private BookDaoImpl bookDao = new BookDaoImpl();

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

    @Override
    public Page<Book> page(Integer pageNo, Integer pageSize) {
        Page<Book> page = new Page<>();
        page.setPageSize(pageSize);
        //设置总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal = pageTotalCount / pageSize;
        if ((pageTotalCount % pageSize) > 0)
            pageTotal++;

        page.setPageTotal(pageTotal);

        //pageNo设置时,需要用到pageTotal的值,所以该操作要放在pageTotal设置后面
        page.setPageNo(pageNo);
        Integer begin = (page.getPageNo() - 1) * pageSize;
        List<Book> books = bookDao.queryForItems(begin, pageSize);

        page.setItems(books);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(Integer pageNo, Integer pageSize, Integer min, Integer max) {
        Page<Book> page = new Page<>();
        page.setPageSize(pageSize);
        //获取满足条件的总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount(min, max);
        page.setPageTotalCount(pageTotalCount);
        //设置总页数
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0)
            pageTotal++;
        //设置有效当前页
        page.setPageTotal(pageTotal);
        if (pageNo <1 ){
            pageNo=1;
        }else if (pageNo > pageTotal){
            pageNo=pageTotal;
        }
        page.setPageNo(pageNo);
        //获取当前页的图书数据
        Integer begin=(pageNo-1)*pageSize;
        List<Book> books = bookDao.queryForItems(begin,pageSize,min,max);
        page.setItems(books);

        return page;
    }
}
