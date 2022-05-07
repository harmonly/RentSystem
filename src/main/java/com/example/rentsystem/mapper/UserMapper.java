package com.example.rentsystem.mapper;

import com.example.rentsystem.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Insert("insert into user(name, account, password, phone) VALUES(#{name}, #{account}, #{password}, #{phone})")
    int addUser(User user);

    @Delete("delete from user where id = #{id}")
    int deleteUser(int id);

    @Select("select * from user")
    List<User> getUsers();

    @Select("select * from user where id = #{id}")
    User getUserById(int id);

    @Select("select * from user where account =#{account} and password = #{password}")
    User getUser(@Param("account") String account, @Param("password") String password);
}
