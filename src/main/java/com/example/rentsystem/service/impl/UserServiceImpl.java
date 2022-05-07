package com.example.rentsystem.service.impl;

import com.example.rentsystem.entity.User;
import com.example.rentsystem.mapper.UserMapper;
import com.example.rentsystem.service.UserService;
import com.example.rentsystem.utils.SqlUtil;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public int addUser(User user) {
        try (SqlSession session = SqlUtil.getSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.addUser(user);
        }
    }

    @Override
    public int deleteUser(int id) {
        try (SqlSession session = SqlUtil.getSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.deleteUser(id);
        }
    }

    @Override
    public List<User> getUsers() {
        try (SqlSession session = SqlUtil.getSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.getUsers();
        }
    }

    @Override
    public User getUserById(int id) {
        try (SqlSession session = SqlUtil.getSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.getUserById(id);
        }
    }

    @Override
    public User getUser(String account, String password) {
        try (SqlSession session = SqlUtil.getSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.getUser(account, password);
        }
    }

    @Override
    public boolean auth(String account, String password, HttpSession session) {
        User user = this.getUser(account, password);
        if (user != null) {
            session.setAttribute("user", user);
            return true;
        }
        return false;
    }

    @Override
    public boolean signup(User user) {
        return this.addUser(user) == 1;
    }
}
