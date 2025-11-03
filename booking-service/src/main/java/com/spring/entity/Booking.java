package com.spring.entity;

import com.spring.domain.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
public class Booking
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  Long customerId;
    private  Long salonId;
    private LocalDateTime startTime;
    private  LocalDateTime endTime;
    @ElementCollection
    private Set<Long> serviceIds;
    private BookingStatus bookingStatus=BookingStatus.PENDING;
    private Double totalPrices;

}
