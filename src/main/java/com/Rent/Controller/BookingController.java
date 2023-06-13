package com.Rent.Controller;


import com.Rent.models.Booking;
import com.Rent.models.Product;
import com.Rent.models.User;
import com.Rent.repository.BookingRepository;
import com.Rent.repository.ProductRepository;
import com.Rent.repository.UserRepository;
import com.Rent.service.EmailService;
import com.Rent.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000" ,allowedHeaders = "*" ,allowCredentials = "true")
@RestController

public class BookingController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

   @Autowired
   private OrderService orderService;
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/products/{id}/bookings")


    public ResponseEntity<String> sendRentalRequest( @PathVariable int id,
                                                     @RequestBody Booking booking) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user1 = userRepository.findByEmail(email);
        Optional<Product> product = productRepository.findById(id);
        System.out.println(id);


        if (bookingRepository.checkIfBookingExists(id, booking.getStartDate(), booking.getEndDate(), user1.getId())) {
          return new ResponseEntity<>("You Have Already send a Booking request for this product", HttpStatus.OK);
        } else {
            User user = product.get().getUser();
            String owner = user.getEmail();
            String pame = product.get().getPname();
            booking.setStatus("PENDING");
            booking.setUser(user);
            booking.setProduct(product.get());


            //int Renter= user1.getId();
            booking.setUser(user1);
            booking.setOwnerId(user.getId());
            bookingRepository.save(booking);


            Date start_date = booking.getStartDate();
            Date end_date = booking.getEndDate();
            String acceptUrl = "http://localhost:8080/bookings/" + booking.getId() + "/accept";
            String denyUrl = "http://localhost:8080/bookings/" + booking.getId() + "/deny";
            String to = owner;
            String subject = "Booking Request From RentMyStuff";
            String message = "<p>Hello,</p>"
                    + "<p>You have received a rental request for your " + pame + ".</p>"
                    + "<p><b>Booking Details:</b></p>"
                    + "<ul>"
                    + "<li>Start Date: " + start_date + "</li>"
                    + "<li>End Date: " + end_date + "</li>"
                    + "<li>Quantity: " + booking.getQuantity() + "</li>"
                    + "</ul>"
                    + "<p>Please click the button below to accept or deny the rental request.</p>"
                    + "<table>"
                    + "<tr>"
                    + "<td><a href=\"" + acceptUrl + "\"><button onclick=\"acceptBooking()\">Accept</button></a></td>"
                    + "<td><a href=\"" + denyUrl + "\"><button onclick=\"acceptBooking()\">Deny</button></a></td>"
                    + "</tr>"
                    + "</table>"
                    + "<script>"
                    + "function acceptBooking() {"
                    + "  button.disabled = true;"
                    + "  alert('Thank u for Accepting rental Request');"
                    + "}"
                    + "function denyBooking(button) {"
                    + "  button.disabled = true;"
                    + "  alert('Sorry !! we will let you know about more renters');"
                    + "}"

                    + "</script>";

            boolean flag = this.emailService.sendEmail(subject, message, to);
            if (flag)
                return new ResponseEntity<>("Booking Request has been send! we will let you know once it get confirmed", HttpStatus.OK);
            else
                return new ResponseEntity<>("Email sending failed", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

       @GetMapping("/bookings/{id}/accept")
        public ResponseEntity<String> acceptRentalRequest(@PathVariable int id) {
            // Retrieve the booking from the database
            Booking booking = bookingRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Booking not found"));

            // Update the booking status to "Accepted"
            booking.setStatus("Accepted");
            bookingRepository.save(booking);
            String name= booking.getUser().getUserName();
            User user1= userRepository.findByUserName(name);
             String pname= booking.getProduct().getPname();
             String owner= user1.getEmail();
            User user = booking.getUser();
            String to = owner;
            System.out.println("OWNER IS"+owner);
            String subject = "Your rental request has been accepted";
            String message = "Dear " + name + ",\n\n"
                    +"<p>Your rental request for " + pname + " has been accepted.</p> "
                    +"<p>Please visit our website to complete your payment.\n\n</p>"
                    + "<p>Thank you for choosing RentMyStuff.\n</p>";
            boolean flag = this.emailService.sendEmail(subject, message, to);
            if(flag) {
                try {

                    orderService.generateOrder(booking);
                } catch (Exception e) {
                    System.out.println("Exception AA gri");
                    e.printStackTrace();
                }
                /*String html = "<html><body><h1>Welcome to my website!</h1></body></html>";
                ResponseEntity<String> response = ResponseEntity.ok()
                        .header("Content-Type", "text/html")
                        .body(html);
                return response;*/
               return ResponseEntity.ok("Thank u for accepting rental request");
            }
                else
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email");
        }


        @GetMapping("/bookings/{id}/deny")
        public ResponseEntity<String> denyRentalRequest(@PathVariable int id)  {
            // Retrieve the booking from the database
            Booking booking = bookingRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Booking not found"));

            booking.setStatus("Denied");
            bookingRepository.save(booking);
            bookingRepository.delete(booking);
           // bookingRepository.save(booking);
            String name= booking.getUser().getUserName();
            User user1= userRepository.findByUserName(name);
            String pname= booking.getProduct().getPname();
            String owner= user1.getEmail();
            User user = booking.getUser();
            String to = owner;
            String subject = "Rental Request Denied";
            String message = "<p> We are sorry to inform you that your rental request has been denied.</p>"
                             +"<p>Please explore more products on RentMyStuff.</p>";

            boolean flag = this.emailService.sendEmail(subject, message, to);
            if(flag)
                return ResponseEntity.ok("We will let u know about other rental requests");
            else
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email");

    }



    }






