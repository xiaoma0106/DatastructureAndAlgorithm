package com.mjh.web.servlet.controller;

import com.mjh.pojo.entity.domain.bean.Book;
import com.mjh.pojo.entity.domain.bean.Page;
import com.mjh.service.impl.BookServiceImpl;
import com.mjh.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mjh
 * @date 2021-10-10 15:35
 */
public class ClientBookServlet extends BaseServlet{
    BookServiceImpl bookService=new BookServiceImpl();

    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer pageNo= WebUtils.parseInt(req.getParameter("pageNo"),1);
        Integer pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        Page<Book> page=bookService.page(pageNo,pageSize);
        page.setUrl("client/bookServlet?action=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    public void pageByPrice(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        Integer pageNo=WebUtils.parseInt(req.getParameter("pageNo"),1);
        Integer pageSize=WebUtils.parseInt(req.getParameter("pageSize"),Page.PAGE_SIZE);
        Integer min=WebUtils.parseInt(req.getParameter("min"),0);
        Integer max=WebUtils.parseInt(req.getParameter("max"),9999);

        Page<Book> page=bookService.pageByPrice(pageNo,pageSize,min,max);
        page.setUrl("client/bookServlet?action=pageByPrice&min="+min+"&max="+max);
        req.setAttribute("page",page);

        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
