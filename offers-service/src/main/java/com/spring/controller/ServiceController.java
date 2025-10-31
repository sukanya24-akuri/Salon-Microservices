package com.spring.controller;

import com.spring.service.ServiceOffersImpl;
import com.spring.entity.ServiceOffers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/offers")
public class ServiceController
{
    private  final ServiceOffersImpl service;

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffers> getOffersById(@PathVariable Long id)
    {
        ServiceOffers offers=service.getServiceById(id);
        return  new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/salon/{id}")
    public ResponseEntity<Set<ServiceOffers>> getOffersBySalonId(@PathVariable Long id)
    {
        Set<ServiceOffers> offers=service.getAllServicesBySalonId(id);
        return  new ResponseEntity<>(offers, HttpStatus.OK);
    }
}
