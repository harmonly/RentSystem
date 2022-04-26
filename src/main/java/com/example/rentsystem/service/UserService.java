package com.example.rentsystem.service;

import com.example.rentsystem.entity.User;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface UserService {

    int addUser(User user);

    int deleteUser(int id);

    List<User> getUsers();

    User getUser(String account, String password);

    boolean auth(String account, String password, HttpSession session);
}
