package com.spring.dto;

import lombok.Data;


@Data
public class CategoryDTO
{
    private Long id;
    private String categoryName;
    private String image;
    private Long salonId;
}
