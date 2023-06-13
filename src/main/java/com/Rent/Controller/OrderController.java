package com.Rent.Controller;

import com.Rent.dto.OrderTableDto;
import com.Rent.models.Booking;
import com.Rent.models.Order_Table;
import com.Rent.repository.BookingRepository;
import com.Rent.repository.OrderRepository;
import com.Rent.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000" ,allowedHeaders = "*", allowCredentials = "true" )
@RestController

public class OrderController {
    private final OrderService orderService;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
   OrderService or;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping ("/generateOrder/{bookingId}")
    public ResponseEntity<OrderTableDto> generateOrder(@PathVariable int bookingId) {
      /*  Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if (bookingOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }*/
        Booking booking = bookingRepository.findById(bookingId).get();
        Order_Table orderTable= orderRepository.findByBookingId(bookingId);

        if (!booking.getStatus().equals("Accepted")) {
            System.out.println("Cannot generate order for a booking that is not Accepted");
            return ResponseEntity.notFound().build();

        }
        Order_Table orderTab = booking.getOrderTable();
        OrderTableDto orderTableDto= or.convertToOrderDto(orderTab);
        return ResponseEntity.ok(orderTableDto);
    }


}
