package com.spring.entity;

import lombok.Data;

import java.util.List;

@Data
public class SalonReport
{
    private Long salonId;

    private Integer totalBookings;
    private  Double totalEarning;
    private Integer cancelledBookings;
    private Double totalRefund;
}
