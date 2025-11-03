package com.spring.service;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.spring.domain.PaymentMethod;
import com.spring.dto.BookingDTO;
import com.spring.dto.UserDTO;
import com.spring.entity.PaymentOrder;
import com.spring.payload.response.PaymentLinkResponse;
import com.stripe.exception.StripeException;

public interface PaymentOrderService
{
PaymentLinkResponse createOrder(UserDTO user,
                                BookingDTO booking,
                                PaymentMethod paymentMethod) throws RazorpayException, StripeException;

PaymentOrder getPaymentByOderId(Long id) throws Exception;
PaymentOrder getPaymentByPaymentId(String paymentId);
PaymentLink creatRazorpayPaymentLink(UserDTO user,
                                     Double amount,
                                     Long orderId) throws RazorpayException;
String creataStripePaymentLink(UserDTO user,
                                                    Double amount,
                                                    Long orderId) throws StripeException;

Boolean proceedPayment(PaymentOrder order, String paymentLink,String paymentId) throws RazorpayException;
}
