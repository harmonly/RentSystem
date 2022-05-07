package com.example.rentsystem.service.impl;

import com.example.rentsystem.entity.Order;
import com.example.rentsystem.mapper.OrderMapper;
import com.example.rentsystem.service.OrderService;
import com.example.rentsystem.utils.SqlUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Override
    public int addOrder(Order order) {
        try (SqlSession session = SqlUtil.getSession()) {
            OrderMapper mapper = session.getMapper(OrderMapper.class);
            return mapper.addOrder(order);
        }
    }

    @Override
    public int deleteOrder(int id) {
        try (SqlSession session = SqlUtil.getSession()) {
            OrderMapper mapper = session.getMapper(OrderMapper.class);
            return mapper.deleteOrder(id);
        }
    }

    @Override
    public Order getOrder(int id) {
        try (SqlSession session = SqlUtil.getSession()) {
            OrderMapper mapper = session.getMapper(OrderMapper.class);
            return mapper.getOrder(id);
        }
    }

    @Override
    public Order getOrderByHouseId(int id) {
        try (SqlSession session = SqlUtil.getSession()) {
            OrderMapper mapper = session.getMapper(OrderMapper.class);
            return mapper.getOrderByHouseId(id);
        }
    }

    @Override
    public List<Order> getOrders() {
        try (SqlSession session = SqlUtil.getSession()) {
            OrderMapper mapper = session.getMapper(OrderMapper.class);
            return mapper.getOrders();
        }
    }

    @Override
    public List<Order> getUserOrders(int id) {
        try (SqlSession session = SqlUtil.getSession()) {
            OrderMapper mapper = session.getMapper(OrderMapper.class);
            return mapper.getUserOrders(id);
        }
    }
}
