package com.spring.controller;

import com.spring.dto.SalonDTO;
import com.spring.dto.UserDTO;
import com.spring.entity.Salon;
import com.spring.mapper.SalonMapper;
import com.spring.service.SalonServiceImpls;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salon")
@RequiredArgsConstructor
public class SalonController
{
    @Autowired
    private final SalonServiceImpls service;

    @PostMapping("/create")
    public ResponseEntity<SalonDTO> createSalon(@RequestBody @Valid SalonDTO salonDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1L);
        Salon salon = service.createSalon(salonDTO, userDTO);
        SalonDTO dto = SalonMapper.mapToSalon(salon);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<SalonDTO> updateSalon(@RequestBody  SalonDTO salonDTO, @PathVariable Long id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1L);
        Salon salon = service.updateSalonById(salonDTO, userDTO, id);
        SalonDTO res = SalonMapper.mapToSalon(salon);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Salon>> getSalons() {
        List<Salon> list = service.getAllSalons();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Salon> getSalonById(@PathVariable Long id) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1L);
        Salon salon = service.getSalonById(id);
        if (salon == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(salon, HttpStatus.OK);

    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<Salon> getSalonByOwnerId(@PathVariable Long id) throws Exception
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1L);
        Salon salon = service.getSalonByOwnerId(id);
        if (salon == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(salon, HttpStatus.OK);

    }

    @GetMapping("/search")
    public ResponseEntity<List<Salon>> searchSalons(@RequestParam String search)
    {
        List<Salon> list = service.searchSalonBYCity(search);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
