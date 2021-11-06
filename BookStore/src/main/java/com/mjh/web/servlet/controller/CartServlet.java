package com.mjh.web.servlet.controller;

import com.google.gson.Gson;
import com.mjh.pojo.entity.domain.bean.Book;
import com.mjh.pojo.entity.domain.bean.Cart;
import com.mjh.pojo.entity.domain.bean.CartItem;
import com.mjh.service.BookService;
import com.mjh.service.impl.BookServiceImpl;
import com.mjh.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mjh
 * @date 2021-10-24 13:05
 */
public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    public void updateCount(HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException{
        Integer id=WebUtils.parseInt(req.getParameter("id"),0);
        Integer count=WebUtils.parseInt(req.getParameter("count"),1);

        Cart cart=(Cart)req.getSession().getAttribute("cart");
        if (cart != null){
            cart.updateCount(id,count);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    public void clearItem(HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException{
        Cart cart=(Cart)req.getSession().getAttribute("cart");
        if (cart != null){
            cart.clearItem();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
    public void deleteItem(HttpServletRequest req,HttpServletResponse resp)throws IOException,ServletException{
        Integer itemId=WebUtils.parseInt(req.getParameter("id"),0);
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            cart.deleteItem(itemId);
        }
        System.out.println(req.getHeader("Referer"));

        resp.sendRedirect(req.getHeader("Referer"));
    }

    public void addItem(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Integer bookId = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(bookId);
        CartItem item = new CartItem(bookId, book.getName(), 1, book.getPrice(), book.getPrice());

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(item);

//        Integer totalCount = cart.getTotalCount();
        String lastName = item.getName();
//        req.setAttribute("totalCount", totalCount);
        req.getSession().setAttribute("lastName", lastName);
//        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    public void ajaxAddItem(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        Integer bookId=WebUtils.parseInt(req.getParameter("id"),0);
        Book book=bookService.queryBookById(bookId);
        CartItem item=new CartItem(bookId,book.getName(),1,book.getPrice(),book.getPrice());
        Cart cart=(Cart)req.getSession().getAttribute("cart");
        if (cart == null){
            cart= new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(item);

        String lastName=book.getName();
        req.getSession().setAttribute("lastName",lastName);
        Integer totalCount=cart.getTotalCount();
        Map<String,String> map=new HashMap<>();
        map.put("lastName",lastName);
        map.put("totalCount",totalCount.toString());

        Gson gson=new Gson();
        String json=gson.toJson(map);
        resp.getWriter().write(json);
    }
}
