package com.spring.service;

import com.spring.dto.SalonDTO;
import com.spring.dto.UserDTO;
import com.spring.entity.Salon;
import com.spring.repository.SalonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalonServiceImpls implements ISalonService {
    private final SalonRepository repository;

    @Override
    public Salon createSalon(SalonDTO salonDTO, UserDTO userDTO) {
        Salon salon = new Salon();
        salon.setName(salonDTO.getName());
        salon.setEmail(salonDTO.getEmail());
        salon.setAddress(salonDTO.getAddress());
        salon.setCity(salonDTO.getCity());
        salon.setImages(salonDTO.getImages());
        salon.setOwnerId(userDTO.getUserId());
        salon.setPhoneNum(salonDTO.getPhoneNum());
        salon.setCloseAt(salonDTO.getCloseAt());
        salon.setOpenAt(salonDTO.getOpenAt());
        return repository.save(salon);
    }

    @Override
    public List<Salon> getAllSalons() {
        return repository.findAll();
    }

    @Override
    public Salon getSalonById(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("salon id is not found"));

    }

    @Override
    public Salon updateSalonById(SalonDTO salonDTO, UserDTO userDTO, Long id) {
        Salon salon = repository.findById(id).orElse(null);
        if (salon != null) {
            salon.setName(salonDTO.getName());
            salon.setEmail(salonDTO.getEmail());
            salon.setAddress(salonDTO.getAddress());
            salon.setCity(salonDTO.getCity());
            salon.setImages(salonDTO.getImages());
            salon.setOwnerId(userDTO.getUserId());
            salon.setPhoneNum(salonDTO.getPhoneNum());
            salon.setCloseAt(salonDTO.getCloseAt());
            salon.setOpenAt(salonDTO.getOpenAt());
            return repository.save(salon);
        }
        throw new RuntimeException("salon id not exist");

    }


    @Override
    public List<Salon> searchSalonBYCity(String city) {
        return repository.searchSalons(city);
    }

    @Override
    public Salon getSalonByOwnerId(Long id) throws Exception {
        Salon ownerId = repository.findByOwnerId(id);
        return ownerId;

    }
}
