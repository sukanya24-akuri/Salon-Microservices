package com.spring.payload.response;

import lombok.Data;

@Data
public class PaymentLinkResponse
{
    private String payment_link_url;
    private String Payment_link_id;
}
