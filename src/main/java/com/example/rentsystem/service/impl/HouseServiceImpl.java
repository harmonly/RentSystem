package com.example.rentsystem.service.impl;

import com.example.rentsystem.entity.House;
import com.example.rentsystem.mapper.HouseMapper;
import com.example.rentsystem.service.HouseService;
import com.example.rentsystem.utils.SqlUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class HouseServiceImpl implements HouseService {

    @Override
    public int addHouse(House house) {
        try (SqlSession session = SqlUtil.getSession()) {
            HouseMapper mapper = session.getMapper(HouseMapper.class);
            return mapper.addHouse(house);
        }
    }

    @Override
    public int deleteHouse(int id) {
        try (SqlSession session = SqlUtil.getSession()) {
            HouseMapper mapper = session.getMapper(HouseMapper.class);
            return mapper.deleteHouse(id);
        }
    }

    @Override
    public House getHouse(int id) {
        try (SqlSession session = SqlUtil.getSession()) {
            HouseMapper mapper = session.getMapper(HouseMapper.class);
            return mapper.getHouse(id);
        }
    }

    @Override
    public List<House> getHouses() {
        try (SqlSession session = SqlUtil.getSession()) {
            HouseMapper mapper = session.getMapper(HouseMapper.class);
            return mapper.getHouses();
        }
    }

    @Override
    public List<House> getUserHouses(int id) {
        try (SqlSession session = SqlUtil.getSession()) {
            HouseMapper mapper = session.getMapper(HouseMapper.class);
            return mapper.getUserHouses(id);
        }
    }

    @Override
    public int editHouse(House house) {
        try (SqlSession session = SqlUtil.getSession()) {
            HouseMapper mapper = session.getMapper(HouseMapper.class);
            return mapper.editHouse(house);
        }
    }
}
