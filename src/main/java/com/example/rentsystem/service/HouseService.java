package com.example.rentsystem.service;

import com.example.rentsystem.entity.House;

import java.util.List;

public interface HouseService {

    int addHouse(House house);

    int deleteHouse(int id);

    House getHouse(int id);

    List<House> getHouses();
}
