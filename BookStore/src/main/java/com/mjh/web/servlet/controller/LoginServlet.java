package com.mjh.web.servlet.controller;

import com.mjh.pojo.entity.domain.bean.User;
import com.mjh.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mjh
 * @date 2021-10-02 17:58
 */
public class LoginServlet extends HttpServlet {
    UserServiceImpl userService=new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
       String username=req.getParameter("username");
       String psd=req.getParameter("password");

        User user=userService.login(new User(username,psd,null));
        if (null == user){
            req.setAttribute("msg","用户名或密码错误!");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            req.getRequestDispatcher("pages/user/login_success.jsp").forward(req,resp);
        }
    }
}
