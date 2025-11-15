package com.spring.service;

import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.spring.domain.PaymentMethod;
import com.spring.domain.PaymentOrderStatus;
import com.spring.dto.BookingDTO;
import com.spring.dto.UserDTO;
import com.spring.entity.PaymentOrder;
import com.spring.payload.response.PaymentLinkResponse;
import com.spring.repository.PaymentOrderRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentOrderImpl implements PaymentOrderService {

    private final PaymentOrderRepository repository;

    @Value("${razorpay.api.key}")
    private String RAZORPAY_ID;
    @Value("${razorpay.api.secret}")
    private String RAZORPAY_SECRET_KEY;
    @Value(("${stripe.api.key}"))
    private String STRIPE_SECRET_KEY;

    @Override
    public PaymentLinkResponse createOrder(UserDTO user, BookingDTO booking, PaymentMethod paymentMethod) throws RazorpayException, StripeException {
        Double totalAmount = booking.getTotalPrices();
        PaymentOrder order = new PaymentOrder();
        order.setAmount(totalAmount);
        order.setPaymentMethod(paymentMethod);
        order.setSalonID(booking.getSalonId());
        order.setCustomerId(booking.getCustomerId());
        PaymentOrder savedOrder = repository.save(order);
        PaymentLinkResponse paymentLinkResponse = new PaymentLinkResponse();
        if (paymentMethod.equals(PaymentMethod.RAZORPAY)) {
            PaymentLink payment = creatRazorpayPaymentLink(user,
                    totalAmount, savedOrder.getId());
            String paymentLink = payment.get("short_url");
            String paymentLinkId = payment.get("id");
            paymentLinkResponse.setPayment_link_url(paymentLink);
            paymentLinkResponse.setPayment_link_id(paymentLinkId);
            savedOrder.setPaymentLinkId(paymentLinkId);
            repository.save(savedOrder);

        } else {
            String paymentLinkUrl = creataStripePaymentLink(user, savedOrder.getAmount(), savedOrder.getId());
            paymentLinkResponse.setPayment_link_url(paymentLinkUrl);
        }

        return paymentLinkResponse;
    }

    @Override
    public PaymentOrder getPaymentByOderId(Long id) throws Exception {
        PaymentOrder paymentOrder=repository.findById(id).orElse(null);
        if(paymentOrder==null)
        {
            throw  new Exception("payment order not found");
        }
        return paymentOrder;
    }

    @Override
    public PaymentOrder getPaymentByPaymentId(String paymentId)
    {
        return repository.findByPaymentLinkId(paymentId);
    }

    @Override
    public PaymentLink creatRazorpayPaymentLink(UserDTO user, Double Amount, Long orderId) throws RazorpayException {
        Double amount=Amount*100;
        RazorpayClient razorpay=new
                RazorpayClient(RAZORPAY_ID,RAZORPAY_SECRET_KEY);
        JSONObject paymentLinkRequest= new JSONObject();
        paymentLinkRequest.put("amount",amount);
        paymentLinkRequest.put("currency","INR");

        JSONObject customer=new JSONObject();
        customer.put("name",user.getFullName());
        customer.put("email",user.getEmail());
        paymentLinkRequest.put("customer",customer);
        JSONObject notify=new JSONObject();
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        paymentLinkRequest.put("callback_url","http://localhost:3000/payment-success/"+orderId);
        paymentLinkRequest.put("callback_method","get");
        PaymentLink paymentLink=razorpay.paymentLink.create(paymentLinkRequest);

        return paymentLink;
    }

    @Override
    public String creataStripePaymentLink(UserDTO user, Double amount, Long orderId) throws StripeException {
        Stripe.apiKey=STRIPE_SECRET_KEY;
        SessionCreateParams sessionCreateParams=SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/payment-success"+orderId)
                .setCancelUrl("http://localhost:3000/payment-fail")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1l)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder().setCurrency("usd")
                                .setUnitAmount((long) (amount*100))
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                        .builder().setName("saloon appoinment booking").build()
                                ).build()
                        ).build()
                ).build();
        Session session=Session.create(sessionCreateParams);

        return session.getUrl();
    }

    @Override
    public Boolean proceedPayment(PaymentOrder paymentOrder, String paymentLink, String paymentId) throws RazorpayException {
        if(paymentOrder.getPaymentOrderStatus().equals(PaymentOrderStatus.PENDING))
        {
         if(paymentOrder.getPaymentMethod().equals(PaymentMethod.RAZORPAY))
         {
             RazorpayClient razorpayClient=new RazorpayClient(RAZORPAY_ID, RAZORPAY_SECRET_KEY);
             Payment payment=razorpayClient.payments.fetch(paymentId);
             Integer amount=payment.get("amount");
             String status=payment.get("status");
             if(status.equals("captured"))
             {
                 //kafka event
                 paymentOrder.setPaymentOrderStatus(PaymentOrderStatus.SUCCESS);
                 repository.save(paymentOrder);
                 return  true;
             }
             return  false;
         }
         else {
             paymentOrder.setPaymentOrderStatus(PaymentOrderStatus.SUCCESS);
             repository.save(paymentOrder);
             return  true;
         }
        }
        return false;
    }

}
