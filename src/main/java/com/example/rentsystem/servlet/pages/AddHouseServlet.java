package com.example.rentsystem.servlet.pages;

import com.example.rentsystem.entity.House;
import com.example.rentsystem.entity.User;
import com.example.rentsystem.service.HouseService;
import com.example.rentsystem.service.impl.HouseServiceImpl;
import com.example.rentsystem.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@WebServlet("/add-house")
public class AddHouseServlet extends HttpServlet {

    HouseService service;

    @Override
    public void init() throws ServletException {
        service = new HouseServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("login-first", true);
            resp.sendRedirect("login");
            return;
        }
        Context context = new Context();
        context.setVariable("user", user);

        if (session.getAttribute("add-house-failure") != null) {
            context.setVariable("add_house_info", "输入信息不完整");
            context.setVariable("add_house_info_style", "color: red;font-weight: bold;");
            session.removeAttribute("add-house-failure");
        }

        ThymeleafUtil.process("add-house.html", context, resp.getWriter());
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("login-first", true);
            resp.sendRedirect("login");
            return;
        }
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String eTime = req.getParameter("e-time");
        String location = req.getParameter("location");

        if (name.equals("") || price.equals("") || eTime.equals("") || location.equals("")) {
            session.setAttribute("add-house-failure", true);
            this.doGet(req, resp);
            return;
        }

        service.addHouse(House.builder()
                .ownerId(user.getId())
                .name(name)
                .location(location)
                .isRent(0)
                .bTime(new Date())
                .eTime(new SimpleDateFormat("yyyy-MM-dd").parse(eTime))
                .price(Integer.parseInt(price))
                .pic(String.format("./assets/img/icon/%s.svg", new Random().nextInt(6) + 1))
                .build());
        resp.sendRedirect("personal-house");
    }
}
