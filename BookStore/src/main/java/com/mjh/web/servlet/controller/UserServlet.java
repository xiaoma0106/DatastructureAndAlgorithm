package com.mjh.web.servlet.controller;

import com.mjh.pojo.entity.domain.bean.User;
import com.mjh.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author mjh
 * @date 2021-10-03 18:23
 */
public class UserServlet extends BaseServlet {

    UserServiceImpl userService = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String psd = req.getParameter("password");

        User user = userService.login(new User(username, psd, null));
        if (null == user) {
            req.setAttribute("msg", "用户名或密码错误!");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String username = req.getParameter("username");
        String psd = req.getParameter("password");
        String repwd = req.getParameter("repwd");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        User user = new User(username, psd, email);

        if (token != null && token.equalsIgnoreCase(code)) {
            if (userService.exitUsername(username)) {
                String msg = "该用户名已存在!";
                req.setAttribute("msg", msg);
                req.setAttribute("email", email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                if (psd.equals(repwd)) {
                    userService.register(user);
                    req.getSession().setAttribute("user", user);
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
                } else {
                    String msg = "两次输入密码不一致!";
                    req.setAttribute("msg", msg);
                    req.setAttribute("username", username);
                    req.setAttribute("email", email);
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
                }
            }
        } else {
            String msg = "验证码[" + code + "]错误";
            req.setAttribute("msg", msg);
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
