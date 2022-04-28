package com.example.rentsystem.servlet.api;

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

@WebServlet("/delete-house")
public class DeleteHouseServlet extends HttpServlet {

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
        service.deleteHouse(Integer.parseInt(req.getParameter("house-id")));
        resp.sendRedirect("personal-house");
    }
}
