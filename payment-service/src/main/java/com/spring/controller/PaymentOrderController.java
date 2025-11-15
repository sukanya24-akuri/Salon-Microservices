package com.spring.controller;

import com.razorpay.RazorpayException;
import com.spring.domain.PaymentMethod;
import com.spring.dto.BookingDTO;
import com.spring.dto.UserDTO;
import com.spring.entity.PaymentOrder;
import com.spring.payload.response.PaymentLinkResponse;
import com.spring.service.PaymentOrderImpl;
import com.spring.service.PaymentOrderService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentOrderController
{
    private final PaymentOrderImpl service;

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPayment(
            @RequestBody BookingDTO booking,
            @RequestParam PaymentMethod paymentMethod
            ) throws StripeException, RazorpayException {
        UserDTO userDTO=new UserDTO();
        userDTO.setUserId(1l);
        userDTO.setFullName("sukanya");
        userDTO.setEmail("sukanya@gmail.com");
        PaymentLinkResponse response=service.createOrder(userDTO,booking,paymentMethod);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentOrder> getPaymentByOrderId(
            @PathVariable Long id
    ) throws Exception {
        PaymentOrder order=service.getPaymentByOderId(id);
        return new ResponseEntity<>(order,HttpStatus.CREATED);
    }

    @PatchMapping("/proceed")
    public ResponseEntity<Boolean> proceedPayment(
            @RequestParam String paymentId,
            @RequestParam String paymentLink
    ) throws Exception {
        PaymentOrder order=service.getPaymentByPaymentId(paymentId);
        Boolean proceed=service.proceedPayment(order,paymentId,paymentLink);
        return new ResponseEntity<>(proceed,HttpStatus.OK);
    }
}
