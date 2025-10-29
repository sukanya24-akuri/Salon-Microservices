package com.spring.service;

import com.spring.dto.SalonDTO;
import com.spring.dto.UserDTO;
import com.spring.entity.Salon;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

public interface ISalonService
{
    Salon createSalon(SalonDTO salonDTO, UserDTO userDTO);
    List<Salon> getAllSalons();
    Salon getSalonById(Long id) throws Exception;
    Salon updateSalonById(SalonDTO salonDTO,UserDTO userDTO,Long id);
    List<Salon> searchSalonBYCity(String city);
    Salon getSalonByOwnerId(Long id) throws Exception;
}
