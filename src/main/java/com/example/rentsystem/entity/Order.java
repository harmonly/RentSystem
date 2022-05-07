package com.example.rentsystem.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Order {

    private int id;
    private int buyId;
    private int ownerId;
    private int houseId;
    private Date bTime;
    private Date eTime;
}