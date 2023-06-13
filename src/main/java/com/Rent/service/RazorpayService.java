package com.Rent.service;

import com.razorpay.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class RazorpayService {

    @Value("${razorpay.keyId}")
    private String razorpayKeyId;

    @Value("${razorpay.keySecret}")
    private String razorpayKeySecret;



    public Order createOrder(BigDecimal amount) throws RazorpayException {
        RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);
        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount.multiply(BigDecimal.valueOf(100)).intValue()); // convert to paise
        params.put("currency", "INR");
        params.put("payment_capture", 1); // auto capture payment
        return razorpayClient.Orders.create((JSONObject) params);
    }

    public Payment capturePayment(String paymentId, BigDecimal amount) throws RazorpayException {
         RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);
        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount.multiply(BigDecimal.valueOf(100)).intValue()); // convert to paise
        return razorpayClient.Payments.capture(paymentId, (JSONObject) params);
    }
}
