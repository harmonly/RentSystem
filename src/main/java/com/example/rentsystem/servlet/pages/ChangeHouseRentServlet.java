package com.example.rentsystem.servlet.pages;

import com.example.rentsystem.entity.House;
import com.example.rentsystem.entity.User;
import com.example.rentsystem.service.HouseService;
import com.example.rentsystem.service.impl.HouseServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/change-house-is-rent")
public class ChangeHouseRentServlet extends HttpServlet {

    HouseService service;

    @Override
    public void init() throws ServletException {
        service = new HouseServiceImpl();
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
        House house = service.getHouse(houseId);
        house.setIsRent(house.getIsRent() == 1 ? 0 : 1);
        service.editHouse(house);
        resp.sendRedirect("user");
    }
}
