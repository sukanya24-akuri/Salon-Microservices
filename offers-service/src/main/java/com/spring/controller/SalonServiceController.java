package com.spring.controller;

import com.spring.dto.CategoryDTO;
import com.spring.dto.SalonDTO;
import com.spring.dto.ServiceDTO;
import com.spring.service.ServiceOffersImpl;
import com.spring.entity.ServiceOffers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salon/owner")
@RequiredArgsConstructor
public class SalonServiceController
{
private final ServiceOffersImpl service;

    @PostMapping("/create")
    public ResponseEntity<ServiceOffers> createServiceOffer(@RequestBody ServiceDTO serviceDTO)
    {
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);

        ServiceOffers offers = service.createService(salonDTO, categoryDTO, serviceDTO);
        return new ResponseEntity<>(offers, HttpStatus.CREATED);
    }

@PostMapping("/update/{id}")
public  ResponseEntity<ServiceOffers> updateServiceOffer(@PathVariable Long id,
                                                         @RequestBody ServiceOffers serviceOffers)
{
    SalonDTO salonDTO = new SalonDTO();
    salonDTO.setId(1L);

    CategoryDTO categoryDTO = new CategoryDTO();
    categoryDTO.setId(1L);
    ServiceOffers update=service.updateService(id,serviceOffers);
    return new ResponseEntity<>(update,HttpStatus.OK);

}
    @DeleteMapping("/delete/{id}")
    public String deleteServiceOffer(@PathVariable Long id)
    {
SalonDTO salonDTO=new SalonDTO();
salonDTO.setId(1L);
        service.deleteServiceById(id,salonDTO.getId());
        return "offer is deleted successfully";
    }

}
