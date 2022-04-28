package com.example.rentsystem.servlet.api;

import com.example.rentsystem.entity.House;
import com.example.rentsystem.entity.User;
import com.example.rentsystem.service.HouseService;
import com.example.rentsystem.service.OrderService;
import com.example.rentsystem.service.impl.HouseServiceImpl;
import com.example.rentsystem.service.impl.OrderServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/change-house-is-rent")
public class ChangeHouseRentServlet extends HttpServlet {

    HouseService houseService;
    OrderService orderService;

    @Override
    public void init() throws ServletException {
        houseService = new HouseServiceImpl();
        orderService = new OrderServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("login-first", true);
            resp.sendRedirect("login");
            return;
        }
        int houseId = Integer.parseInt(req.getParameter("house-id"));
        House house = houseService.getHouse(houseId);
        if (house.getIsRent() == 1) {
            house.setIsRent(0);
            orderService.deleteOrder(orderService.getOrderByHouseId(houseId).getId());
        } else if (house.getIsRent() == 0) {
            resp.sendRedirect("personal-house");
            return;
        }
        houseService.editHouse(house);
        resp.sendRedirect("personal-house");
    }
}
