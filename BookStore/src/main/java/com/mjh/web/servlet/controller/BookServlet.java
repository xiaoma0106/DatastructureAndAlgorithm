package com.mjh.web.servlet.controller;

import com.mjh.pojo.entity.domain.bean.Book;
import com.mjh.pojo.entity.domain.bean.Page;
import com.mjh.service.impl.BookServiceImpl;
import com.mjh.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author mjh
 * @date 2021-10-09 10:40
 */
public class BookServlet extends BaseServlet{
    BookServiceImpl bookService=new BookServiceImpl();

    //更具指定id更新图书信息
    public void update(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        Integer id=Integer.parseInt(req.getParameter("id"));

        String name=req.getParameter("book_name");
        BigDecimal price=new BigDecimal(req.getParameter("book_price"));
        String author=req.getParameter("book_author");
        Integer sales=Integer.parseInt(req.getParameter("book_sales"));
        Integer stock=Integer.parseInt(req.getParameter("book_stock"));
        Book book=new Book(id,name,price,author,sales,stock,"");

        bookService.updateBook(book);

        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));

    }
    //获取指定id的图书信息用于回显
    public void getBook(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        Integer id=Integer.parseInt(req.getParameter("id"));
        Book book=bookService.queryBookById(id);
        req.setAttribute("book",book);

        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    //删除一个图书信息
    public void del(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        Integer id=Integer.parseInt(req.getParameter("id"));
        bookService.deleteBookById(id);

        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));

    }
    //添加一个图书
    public void add(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        String name=req.getParameter("book_name");
        BigDecimal price=new BigDecimal(req.getParameter("book_price"));
        String author=req.getParameter("book_author");
        Integer sales=Integer.parseInt(req.getParameter("book_sales"));
        Integer stock=Integer.parseInt(req.getParameter("book_stock"));
        Book book=new Book(null,name,price,author,sales,stock,"");
        bookService.addBook(book);

        //添加完成后,显示所有图书信息
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page");
//        list(req,resp);
    }


    //获取所有图书数据
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books=bookService.queryBooks();
        req.setAttribute("books",books);

        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }


    //获取指定页面的图书数据
    public void page(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        Integer pageNo= WebUtils.parseInt(req.getParameter("pageNo"),1);
        Integer pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        Page<Book> page=bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        req.setAttribute("page",page);

        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);

    }
}
