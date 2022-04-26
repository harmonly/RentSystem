package com.example.rentsystem.mapper;

import com.example.rentsystem.entity.House;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface HouseMapper {

    @Insert("insert into house(owner_id, name, price, location, is_rent) " +
            "VALUES(#{ownerId}, #{name}, #{price}, #{location}, #{isRent})")
    int addHouse(House house);

    @Delete("delete from house where id = #{id}")
    int deleteHouse(int id);

    @Results({
            @Result(column = "owner_id", property = "ownerId"),
            @Result(column = "is_rent", property = "isRent")
    })
    @Select("select * from house where id = #{id}")
    House getHouse(int id);

    @Results({@Result(column = "is_rent", property = "isRent")})
    @Select("select * from house")
    List<House> getHouses();
}
