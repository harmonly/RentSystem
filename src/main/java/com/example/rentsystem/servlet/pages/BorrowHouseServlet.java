package com.example.rentsystem.servlet.pages;

import com.example.rentsystem.service.HouseService;
import com.example.rentsystem.service.impl.HouseServiceImpl;
import com.example.rentsystem.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.Thymeleaf;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/borrow-house")
public class BorrowHouseServlet extends HttpServlet {

    HouseService service;

    @Override
    public void init() throws ServletException {
        service = new HouseServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int houseId = Integer.parseInt(req.getParameter("house-id"));
        Context context = new Context();
        context.setVariable("house", service.getHouse(houseId));
        ThymeleafUtil.process("borrow-house.html", context, resp.getWriter());
    }
}
