package com.example.rentsystem.servlet.pages;

import com.example.rentsystem.entity.User;
import com.example.rentsystem.service.HouseService;
import com.example.rentsystem.service.impl.HouseServiceImpl;
import com.example.rentsystem.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    HouseService houseService;

    @Override
    public void init() throws ServletException {
        houseService = new HouseServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            req.getSession().setAttribute("login-first", true);
            resp.sendRedirect("login");
            return;
        }
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("user_houses", houseService.getUserHouses(user.getId()));
        ThymeleafUtil.process("user.html", context, resp.getWriter());
    }
}
