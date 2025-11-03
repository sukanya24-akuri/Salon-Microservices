package com.spring.repository;

import com.spring.entity.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder,Long>
{
    PaymentOrder findByPaymentLinkId(String paymentLinkId);
}
