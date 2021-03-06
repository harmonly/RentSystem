package com.example.rentsystem.servlet.auth;

import com.example.rentsystem.service.UserService;
import com.example.rentsystem.service.impl.UserServiceImpl;
import com.example.rentsystem.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            String username = null;
            String password = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) username = cookie.getValue();
                if (cookie.getName().equals("password")) password = cookie.getValue();
            }
            if (username != null && password != null) {
                if (service.auth(username, password, req.getSession())) {
                    resp.sendRedirect("index");
                    return;   //直接返回
                }
            }
        }

        Context context = new Context();
        HttpSession session = req.getSession();
        // 已经登录
        if (session.getAttribute("user") != null) {
            resp.sendRedirect("index");
            return;
        }

        String loginInfo = "输入用户名和密码进行登录";
        String loginInfoStyle = "";
        if (session.getAttribute("login-first") != null) {
            loginInfo = "请先登录再操作";
            loginInfoStyle = "color: red;font-weight: bold;";
            session.removeAttribute("login-first");
        }
        if (session.getAttribute("login-failure") != null) {
            loginInfo = "您输入的用户名或密码错误";
            loginInfoStyle = "color: red;font-weight: bold;";
            session.removeAttribute("login-failure");
        }

        context.setVariable("login_info", loginInfo);
        context.setVariable("login_info_style", loginInfoStyle);

        ThymeleafUtil.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String keepLog = req.getParameter("keep-log");
        if (service.auth(username, password, req.getSession())) {
            if (keepLog != null) {
                Cookie cookie_username = new Cookie("username", username);
                cookie_username.setMaxAge(60 * 60 * 24 * 7);
                Cookie cookie_password = new Cookie("password", password);
                cookie_password.setMaxAge(60 * 60 * 24 * 7);
                resp.addCookie(cookie_username);
                resp.addCookie(cookie_password);
            }
            resp.sendRedirect("index");
        } else {
            req.getSession().setAttribute("login-failure", true);
            this.doGet(req, resp);
        }
    }
}
