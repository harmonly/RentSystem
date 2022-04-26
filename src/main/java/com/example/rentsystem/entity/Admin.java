package com.example.rentsystem.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Admin {

    private int id;
    private String name;
    private String password;
}
