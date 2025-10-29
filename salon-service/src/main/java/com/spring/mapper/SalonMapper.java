package com.spring.mapper;

import com.spring.dto.SalonDTO;
import com.spring.entity.Salon;

public class SalonMapper
{
    public static SalonDTO mapToSalon(Salon salon)
    {
        SalonDTO salon1=new SalonDTO();
        salon1.setId(salon.getId());
        salon1.setName(salon.getName());
        salon1.setEmail(salon.getEmail());
        salon1.setAddress(salon.getAddress());
        salon1.setCity(salon.getCity());
        salon1.setImages(salon.getImages());
        salon1.setOwnerId(salon.getOwnerId());
        salon1.setPhoneNum(salon.getPhoneNum());
        salon1.setCloseAt(salon.getCloseAt());
        salon1.setOpenAt(salon.getOpenAt());
        return salon1;
    }
}
