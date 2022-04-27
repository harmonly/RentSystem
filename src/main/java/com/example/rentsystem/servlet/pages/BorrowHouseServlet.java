package com.example.rentsystem.servlet.pages;

import com.example.rentsystem.entity.House;
import com.example.rentsystem.entity.User;
import com.example.rentsystem.service.HouseService;
import com.example.rentsystem.service.UserService;
import com.example.rentsystem.service.impl.HouseServiceImpl;
import com.example.rentsystem.service.impl.UserServiceImpl;
import com.example.rentsystem.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/borrow-house")
public class BorrowHouseServlet extends HttpServlet {

    HouseService houseService;
    UserService userService;

    @Override
    public void init() throws ServletException {
        houseService = new HouseServiceImpl();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            req.getSession().setAttribute("login-first", true);
            resp.sendRedirect("login");
            return;
        }
        int houseId = Integer.parseInt(req.getParameter("house-id"));
        Context context = new Context();
        House house = houseService.getHouse(houseId);
        context.setVariable("user", user);
        context.setVariable("house", house);
        context.setVariable("phone", user.getPhone());
        ThymeleafUtil.process("borrow-house.html", context, resp.getWriter());
    }
}
