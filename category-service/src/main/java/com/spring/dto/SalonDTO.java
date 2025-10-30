package com.spring.dto;


import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class SalonDTO
{
    private  Long id;
    private  String name;
    private  String address;
    private  String city;
    private  String phoneNum;
    private  String email;
    private Long ownerId;
    private List<String> images;
    private LocalTime openAt;
    private LocalTime closeAt;

}
