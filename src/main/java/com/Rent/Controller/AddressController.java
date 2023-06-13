package com.Rent.Controller;

import com.Rent.models.Address;
import com.Rent.models.Order_Table;
import com.Rent.models.User;
import com.Rent.repository.AddressRepository;
import com.Rent.repository.OrderRepository;
import com.Rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000" ,allowedHeaders = "*" ,allowCredentials = "true")
@RestController

public class AddressController {


@Autowired
private AddressRepository addressRepository;

@Autowired
private UserRepository userRepository;
@Autowired
private OrderRepository orderRepository;

    @PostMapping("/proceedtocheckout/{orderId}")
    public ResponseEntity<String> addAddress( @PathVariable int orderId ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emai = authentication.getName();
        User user = userRepository.findByEmail(emai);
       // System.out.println("Email is"+user.getEmail());
     //   System.out.println(user.getId());
        Address address= addressRepository.findByUserId(user.getId());
        Order_Table ob= orderRepository.findById(orderId);
        System.out.println(orderId);
        ob.setAddress(address);
        orderRepository.save(ob);
        return ResponseEntity.ok("Address registered successfully");

    }

}
