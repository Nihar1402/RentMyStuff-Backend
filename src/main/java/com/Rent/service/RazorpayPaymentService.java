package com.Rent.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RazorpayPaymentService {


    public Order createOrder( Map<String, Object> data) throws RazorpayException {

        Long amt=Long.parseLong(data.get("amount").toString());
        RazorpayClient client = new RazorpayClient("rzp_test_401cmKXEmF2LUE","GDEjcUzDFhX5UWhjDMlGeffO");
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amt*100);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "order_rcpttx87_");
        return client.Orders.create(orderRequest);

    }


}
