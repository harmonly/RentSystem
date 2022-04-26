package com.example.rentsystem.mapper;

import com.example.rentsystem.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderMapper {

    @Insert("insert into `order`(buy_id, owner_id, house_id, b_time, e_time) " +
            "VALUES(#{buyId}, #{ownerId}, #{houseId}, #{bTime}, #{eTime})")
    int addOrder(Order order);

    @Delete("delete from `order` where id = #{id}")
    int deleteOrder(int id);

    @Results({
            @Result(column = "buy_id", property = "buyId"),
            @Result(column = "owner_id", property = "ownerId"),
            @Result(column = "house_id", property = "houseId"),
            @Result(column = "b_time", property = "bTime"),
            @Result(column = "e_time", property = "eTime"),
    })
    @Select("select * from `order` where id = #{id}")
    Order getOrder(int id);

    @Results({
            @Result(column = "buy_id", property = "buyId"),
            @Result(column = "owner_id", property = "ownerId"),
            @Result(column = "house_id", property = "houseId"),
            @Result(column = "b_time", property = "bTime"),
            @Result(column = "e_time", property = "eTime"),
    })
    @Select("select * from `order`")
    List<Order> getOrders();
}
