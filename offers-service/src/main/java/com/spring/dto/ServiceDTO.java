package com.spring.dto;

import lombok.Data;

@Data
public class ServiceDTO
{
    private Long id;
    private String name;
    private String description;
    private String image;
    private Double price;
    private Long salonId;
    private Long categoryID;
    private Integer duration;
}
