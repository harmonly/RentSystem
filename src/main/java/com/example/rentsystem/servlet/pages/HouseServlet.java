package com.example.rentsystem.servlet.pages;

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

@WebServlet("/house")
public class HouseServlet extends HttpServlet {

    HouseService service;

    @Override
    public void init() throws ServletException {
        service = new HouseServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        context.setVariable("user", req.getSession().getAttribute("user"));
        context.setVariable("house_list", service.getHouses());
        ThymeleafUtil.process("house.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        String location= req.getParameter("location");
        Context context = new Context();
        context.setVariable("user", req.getSession().getAttribute("user"));
        context.setVariable("house_list", service.findHousesByKeyWord(keyword));
        context.setVariable("house_list", service.findHousesByLocation(location));
        ThymeleafUtil.process("house.html", context, resp.getWriter());
    }
}
