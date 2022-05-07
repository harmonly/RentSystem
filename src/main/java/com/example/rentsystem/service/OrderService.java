package com.example.rentsystem.service;

import com.example.rentsystem.entity.Order;

import java.util.List;

public interface OrderService {

    int addOrder(Order order);

    int deleteOrder(int id);

    Order getOrder(int id);

    Order getOrderByHouseId(int id);

    List<Order> getOrders();

    List<Order> getUserOrders(int id);
}
