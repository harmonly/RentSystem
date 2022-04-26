package com.example.rentsystem.servlet.auth;

import com.example.rentsystem.entity.User;
import com.example.rentsystem.service.UserService;
import com.example.rentsystem.service.impl.UserServiceImpl;
import com.example.rentsystem.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/sign-up")
public class RegisterServlet extends HttpServlet {

    UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Context context = new Context();

        String signUpInfo = "创建您的账号进入网站";
        String signUpInfoStyle = "";
        if (session.getAttribute("password-not-equal") != null) {
            signUpInfo = "两次密码不一致";
            signUpInfoStyle = "color: red;font-weight: bold;";

        } else if (session.getAttribute("signup-failure") != null) {
            signUpInfo = "注册失败";
            signUpInfoStyle = "color: red;font-weight: bold;";
        }

        context.setVariable("sign_up_info", signUpInfo);
        context.setVariable("sign_up_info_style", signUpInfoStyle);

        ThymeleafUtil.process("sign-up.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String username = req.getParameter("username");
        String account = req.getParameter("account");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm-password");

        if (!password.equals(confirmPassword)) {
            session.setAttribute("password-not-equal", true);
            this.doGet(req, resp);
        } else {
            if (service.signup(User.builder()
                    .name(username)
                    .account(account)
                    .password(password)
                    .phone(phone)
                    .build())) {
                session.removeAttribute("signup-failure");
                resp.sendRedirect("login");
            } else {
                session.setAttribute("signup-failure", true);
                this.doGet(req, resp);
            }
        }
    }
}
