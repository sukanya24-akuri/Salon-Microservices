package com.spring.dto;


import com.spring.domain.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data

    public class BookingDTO
    {

        private Long id;
        private  Long customerId;
        private  Long salonId;
        private LocalDateTime startTime;
        private  LocalDateTime endTime;
        private Set<Long> serviceIds;
        private BookingStatus bookingStatus=BookingStatus.PENDING;
        private Double totalPrices;

    }


