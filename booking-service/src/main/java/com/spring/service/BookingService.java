package com.spring.service;

import com.spring.domain.BookingStatus;
import com.spring.dto.BookingRequest;
import com.spring.dto.SalonDTO;
import com.spring.dto.ServiceDTO;
import com.spring.dto.UserDTO;
import com.spring.entity.Booking;
import com.spring.entity.SalonReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {
    Booking createBooking(SalonDTO salonDTO,
                          UserDTO userDTO,
                          Set<ServiceDTO> serviceIds,
                          BookingRequest request) throws Exception;


    List<Booking> getBookingsByCustomerId(Long id);
    List<Booking> getBookingsBySalonId(Long id);
    Booking getBookingById(Long id);
    Booking updateBooking(Long id, BookingStatus status);
    List<Booking> getBookingsByDate(Long id, LocalDate date);
    SalonReport getReportsBySalonId(Long id);
}
