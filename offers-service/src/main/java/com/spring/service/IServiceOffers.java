package com.spring.service;

import com.spring.dto.CategoryDTO;
import com.spring.dto.SalonDTO;
import com.spring.dto.ServiceDTO;
import com.spring.entity.ServiceOffers;

import java.util.Set;

public interface IServiceOffers
{
    ServiceOffers createService(SalonDTO salonDTO, CategoryDTO categoryDTO, ServiceDTO serviceDTO);

    ServiceOffers updateService(Long id,ServiceOffers serviceOffers);
    ServiceOffers getServiceById(Long id);

    Set<ServiceOffers> getAllServicesBySalonId(Long id);
    void deleteServiceById(Long id,Long salonDTO);

}
