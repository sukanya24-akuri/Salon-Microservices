package com.spring.entity;


import com.spring.domain.PaymentMethod;
import com.spring.domain.PaymentOrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PaymentOrder
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private Long customerId;
    private Double amount;
    private Long bookingId;
    private Long salonID;
    private PaymentOrderStatus paymentOrderStatus;
    private PaymentMethod paymentMethod;
    private String paymentLinkId;
}
