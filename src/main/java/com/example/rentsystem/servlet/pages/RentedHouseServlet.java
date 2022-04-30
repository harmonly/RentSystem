package com.example.rentsystem.servlet.pages;

import com.example.rentsystem.entity.House;
import com.example.rentsystem.entity.User;
import com.example.rentsystem.service.HouseService;
import com.example.rentsystem.service.OrderService;
import com.example.rentsystem.service.impl.HouseServiceImpl;
import com.example.rentsystem.service.impl.OrderServiceImpl;
import com.example.rentsystem.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/rented-house")
public class RentedHouseServlet extends HttpServlet {

    HouseService houseService;
    OrderService orderService;

    @Override
    public void init() throws ServletException {
        houseService = new HouseServiceImpl();
        orderService = new OrderServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            req.getSession().setAttribute("login-first", true);
            resp.sendRedirect("login");
            return;
        }
        List<House> list = new ArrayList<>();
        orderService.getUserOrders(user.getId()).forEach(order -> list.add(houseService.getHouse(order.getHouseId())));

        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("user_rented_houses", list);
        ThymeleafUtil.process("rented-house.html", context, resp.getWriter());
    }
}
