package com.mjh.web.servlet.controller;

import com.mjh.pojo.entity.domain.bean.Cart;
import com.mjh.pojo.entity.domain.bean.User;
import com.mjh.service.OrderService;
import com.mjh.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mjh
 * @date 2021-10-30 17:03
 */
public class OrderServlet extends BaseServlet{
    private OrderService orderService=new OrderServiceImpl();

    public void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User loginUser=(User) req.getSession().getAttribute("user");
        if (loginUser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        Cart cart= (Cart) req.getSession().getAttribute("cart");

        String orderId=orderService.createOrder(cart,loginUser.getId());

        req.getSession().setAttribute("orderId",orderId);

        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

}
