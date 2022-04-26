package com.example.rentsystem.mapper;

import com.example.rentsystem.entity.Admin;
import org.apache.ibatis.annotations.Select;

public interface AdminMapper {

    @Select("select * from admin where id = #{id}")
    Admin getAdmin(int id);
}
