package com.example.rentsystem.mapper;

import com.example.rentsystem.entity.House;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface HouseMapper {

    @Insert("insert into house(owner_id, name, price, location, is_rent, b_time, e_time, pic) " +
            "VALUES(#{ownerId}, #{name}, #{price}, #{location}, #{isRent}, #{bTime}, #{eTime}, #{pic})")
    int addHouse(House house);

    @Delete("delete from house where id = #{id}")
    int deleteHouse(int id);

    @Results({
            @Result(column = "owner_id", property = "ownerId"),
            @Result(column = "is_rent", property = "isRent"),
            @Result(column = "b_time", property = "bTime"),
            @Result(column = "e_time", property = "eTime")
    })
    @Select("select * from house where id = #{id}")
    House getHouse(int id);

    @Results({
            @Result(column = "owner_id", property = "ownerId"),
            @Result(column = "is_rent", property = "isRent"),
            @Result(column = "b_time", property = "bTime"),
            @Result(column = "e_time", property = "eTime")
    })
    @Select("select * from house")
    List<House> getHouses();

    @Results({
            @Result(column = "owner_id", property = "ownerId"),
            @Result(column = "is_rent", property = "isRent"),
            @Result(column = "b_time", property = "bTime"),
            @Result(column = "e_time", property = "eTime")
    })
    @Select("select * from house where owner_id = #{id}")
    List<House> getUserHouses(int id);

    @Update("update house set owner_id = #{ownerId}, is_rent = #{isRent}, b_time = #{bTime}, e_time = #{eTime}, pic = #{pic} where id = #{id}")
    int editHouse(House house);
}
