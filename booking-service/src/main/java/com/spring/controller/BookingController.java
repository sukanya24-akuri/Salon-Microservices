package com.spring.controller;

import com.spring.domain.BookingStatus;
import com.spring.dto.BookingRequest;
import com.spring.dto.SalonDTO;
import com.spring.dto.ServiceDTO;
import com.spring.dto.UserDTO;
import com.spring.entity.Booking;
import com.spring.entity.SalonReport;
import com.spring.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController
{
    private final BookingService service;

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(
    @RequestBody BookingRequest bookingRequest) throws Exception {
        UserDTO userDTO=new UserDTO();
        userDTO.setUserId(2L);
        SalonDTO salonDTO=new SalonDTO();
        salonDTO.setId(1L);
        salonDTO.setOpenAt(LocalTime.now());
        salonDTO.setCloseAt(LocalTime.now().plusHours(10));
        Set<ServiceDTO> set=new HashSet<>();
        ServiceDTO serviceDTO=new ServiceDTO();
        serviceDTO.setName("hair cut");
        serviceDTO.setPrice(500d);
        serviceDTO.setDuration(30);
        serviceDTO.setId(1l);
        set.add(serviceDTO);
        Booking createdBooking=service
                .createBooking(salonDTO,userDTO,set,bookingRequest);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id,
                                                 @RequestParam BookingStatus status)
    {
   Booking updateBooking=service.updateBooking(id,status);
   return  new ResponseEntity<>(updateBooking,HttpStatus.CREATED);
    }

    @GetMapping("/salon/{id}")
    public ResponseEntity<List<Booking>> getBookingBySalon(@PathVariable Long id)
    {
        List<Booking> list=service.getBookingsBySalonId(id);
        return  new ResponseEntity<>(list,HttpStatus.CREATED);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Booking>> getBookingByCustomerId(@PathVariable Long id)
    {
        UserDTO userDTO= new UserDTO();
        userDTO.setUserId(1l);
        List<Booking> user=service.getBookingsByCustomerId(id);
        return  new ResponseEntity<>(user,HttpStatus.CREATED);
    }
    @GetMapping("/book/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id)
    {
        Booking bookingId=service.getBookingById(id);
        return  new ResponseEntity<>(bookingId,HttpStatus.CREATED);
    }

    @GetMapping("/reports/{id}")
    public ResponseEntity<SalonReport> getSalonReports(@PathVariable  Long id)
    {

        SalonReport report=service.getReportsBySalonId(id);
        return new ResponseEntity<>(report,HttpStatus.OK);
    }

    @GetMapping("/date/{id}")
    public ResponseEntity<List<Booking>> getBookingsByDate(
            @PathVariable Long id,
            @RequestParam LocalDate date
            )
    {
        List<Booking> list=service.getBookingsByDate(id,date);
        return new ResponseEntity<>(list,HttpStatus.CREATED);
    }



}
