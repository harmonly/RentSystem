package com.example.rentsystem.servlet.api;

import com.example.rentsystem.entity.House;
import com.example.rentsystem.entity.Order;
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
import java.util.Date;

@WebServlet("/add-order")
public class AddOrderServlet extends HttpServlet {

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
        String months = req.getParameter("months");
        String houseId = req.getParameter("house-id");
        if (months.equals("") || houseId.equals("")) {
            resp.sendRedirect("house");
            return;
        }
        House house = houseService.getHouse(Integer.parseInt(houseId));
        Date eTime = new Date();
        eTime.setTime(eTime.getTime() + (long) Integer.parseInt(months) * 30 * 24 * 60 * 60 * 1000);
        orderService.addOrder(Order.builder()
                .buyId(user.getId())
                .houseId(house.getId())
                .ownerId(house.getOwnerId())
                .bTime(new Date())
                .eTime(eTime)
                .build());
        house.setIsRent(house.getIsRent() == 1 ? 0 : 1);
        houseService.editHouse(house);
        resp.sendRedirect("house");
    }
}
