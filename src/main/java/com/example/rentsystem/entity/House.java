package com.example.rentsystem.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class House {

    private int id;
    private int ownerId;
    private String name;
    private double price;
    private String location;
    // 0=未出租 1=出租
    private int isRent;
}
