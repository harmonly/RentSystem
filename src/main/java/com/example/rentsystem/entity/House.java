package com.example.rentsystem.entity;

import lombok.Builder;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Builder
public class House {

    private int id;
    private int ownerId;
    private String name;
    private int price;
    private String location;
    // 0=未出租 1=出租
    private int isRent;
    private Date bTime;
    private Date eTime;
    private String pic;

    // 将bTime格式化
    public String parseBTime() {
        return new SimpleDateFormat("yyyy-MM-dd").format(bTime);
    }

    // 将eTime格式化
    public String parseETime() {
        return new SimpleDateFormat("yyyy-MM-dd").format(eTime);
    }
}
