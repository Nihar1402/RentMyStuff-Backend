package com.Rent.Controller;
import java.util.*;
import com.Rent.dto.PaymentRequestDto;
import com.Rent.models.Booking;
import com.Rent.models.Order_Table;
import com.Rent.models.Product;
import com.Rent.models.Transaction;
import com.Rent.repository.BookingRepository;
import com.Rent.repository.OrderRepository;
import com.Rent.repository.ProductRepository;
import com.Rent.repository.TransactionRepository;
import com.Rent.service.EmailService;
import com.Rent.service.RazorpayPaymentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import com.razorpay.*;

@CrossOrigin(origins = "http://localhost:3000" ,allowedHeaders = "*" ,allowCredentials = "true")
@RestController
public class PaymentController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private BookingRepository bookingRepository;


    @Autowired
    private RazorpayPaymentService razorpayPaymentService;
    @Autowired
    private TransactionRepository transactionRepository;


   @Autowired
   private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;


    //	return order.toString();

    @PostMapping("/razorpay/create-order")
    public ResponseEntity<String>  createOrder(@RequestBody Map<String, Object> data) {
        int aid=0;
        System.out.println(data);
         System.out.println(data.get("info"));
        Integer str = (Integer) data.get("info");
        System.out.println(str);
        aid= str.intValue();


       // int i = Integer.valueOf((String) data.get("bookingId"));

     System.out.println(aid);
        try {
            Order order = razorpayPaymentService.createOrder(data);
           // Transaction transaction = new Transaction();
           // transaction.setPaymentId(order.get("id"));
          //    transaction.setOrderId(aid);
          //  transaction.setStatus("created");
         //  Order_Table od= orderRepository.findById(aid);

           // transactionRepository.save(transaction);

            return new ResponseEntity<>(order.toString(), HttpStatus.OK);


        }
        catch (Exception e)
        {
            return  null;
        }


    }


    @PostMapping("/payment/capture")
    public ResponseEntity<?> capturePayment(@RequestBody Map<String,Object> data) {
        //System.out.println("helooo " + data.get("order_id"));
       Integer str = (Integer) data.get("order_id");
       System.out.println(str);
      int aid= str.intValue();

        System.out.println(aid);
       try {

           Order_Table od= orderRepository.findById(aid);
           Product product= od.getBooking().getProduct();

            Transaction transaction = new Transaction();
            //transaction.setPaymentId(order.get("id"));
           //    transaction.setOrderId(aid);
           //  transaction.setStatus("created");
           //  Order_Table od= orderRepository.findById(aid);

           // transactionRepository.save(transaction);
           //List<  Transaction>  transa = transactionRepository.findByOrderId(aid);
      //    Transaction transaction= transa.get(0);

            transaction.setPaymentId(data.get("payment_id").toString());
            transaction.setOrderId(aid);
            transaction.setStatus(data.get("status").toString());
            transactionRepository.save(transaction);


            System.out.println(transaction.getStatus());

            if(transaction.getStatus().equals( "paid"))
            {
                od.setTransactions(transaction);
                this.orderRepository.save(od);
                product.setAvailable(false);
                productRepository.save(product) ;
                Booking booking = bookingRepository.findByOrderTableId(aid);
                booking.setStatus("PAID");
                bookingRepository.save(booking);
              //  Product product= booking.getProduct();
               // product.setAvailable(false);
                //orderRepository.save(product);

                      //  .orElseThrow(() -> new RuntimeException("Booking not found"));

               // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String emai =booking.getUser().getEmail();

                String subject = "Payment Confirmation - Rent My Stuff";

                String message = "Dear " + booking.getUser().getUserName() + ",\n\n"
                        + "Your payment for " + booking.getProduct().getPname() + " has been completed.!\n\n"
                        +" Your product will be delivered soon."
                        + "Thank you for using Rent My Stuff!";

                boolean flag = this.emailService.sendEmail(subject, message, emai);
                if(flag) {

                    System.out.println("Email send successfully");

                }

            }

            return ResponseEntity.ok("Updated");
        } catch (Exception e) {
            System.out.println("Exception aa gyi");
            e.printStackTrace();

        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }



}