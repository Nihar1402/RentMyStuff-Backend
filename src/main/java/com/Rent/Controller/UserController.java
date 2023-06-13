package com.Rent.Controller;

import com.Rent.dto.UserProfile;
import com.Rent.dto.UserProfileDto;
import com.Rent.models.Address;
import com.Rent.repository.AddressRepository;
import com.Rent.repository.UserRepository;
import com.Rent.service.UserServing;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import com.Rent.repository.UserRepository;
import com.Rent.dto.LoginDto;
import com.Rent.models.User;
import com.Rent.service.UserService;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@CrossOrigin(origins = "http://localhost:3000" ,allowedHeaders = "*", allowCredentials = "true")
@RestController

public class UserController {
    @Autowired
    private UserServing userServing;

    @Autowired
    private AddressRepository addressRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;


    @PostMapping("/signup")

    public ResponseEntity<String> signUp(@RequestParam(value = "userPic", required = false) MultipartFile file,
                                         @RequestParam String email,
                                         @RequestParam String userName,
                                         @RequestParam String userPhone,
                                         @RequestParam String password,
                                         @RequestParam String city,
                                         @RequestParam String state,
                                         @RequestParam String pincode,
                                         @RequestParam String userAddress) {


        User user = new User();
        user.setEmail(email);
        user.setUserName(userName);
        user.setUserPhone(userPhone);
        user.setPassword(password);
        user.setUserPic("contact.png");

        // user.setCity(city);
        //user.setState(state);
        //user.setPincode(pincode);
        //user.setUserAddress(userAddress);

        if (userService.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already in use");
        } else {

            userService.save(user);
            Address address= new Address();
            address.setUser(user);
            addressRepository.save(address);
            return ResponseEntity.ok("User signed up successfully!");


        }

    }




    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");

        }

    }





    @PostMapping("/myprofile")
    public UserProfile getCurrentUserProfile(Authentication authentication) throws IOException {
        String email = authentication.getName();
        User us = userRepository.findByEmail(email);
        Address ad = addressRepository.findByUserId(us.getId());
        UserProfile user = userServing.convertToUserDto(us, ad);


        // System.out.println("Use Email Id Is" + user.getEmail());
        return user;
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseEntity.badRequest().body("Session not found");
        }

        try {
            session.invalidate();
            response.setHeader("X-Logout-Successful", "true");


            return ResponseEntity.ok()
                    .header("X-Logout-Successful", "true")
                    .body("Logout successful");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Logout failed: " + e.getMessage());
        }
    }

    @PostMapping("/updateProfile")
    public ResponseEntity<String> updateUserProfile(@RequestParam( value = "userPic" ,required = false ) MultipartFile file,
                                                    @RequestParam String userName,
                                                    @RequestParam String userPhone,
                                                    @RequestParam String city,
                                                    @RequestParam String state,
                                                    @RequestParam String pincode,
                                                    @RequestParam String userAddress,
                                                    HttpSession session) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emai = authentication.getName();
        User user = userRepository.findByEmail(emai);
        Address address=addressRepository.findByUserId(user.getId());
        System.out.println("YE RH AADDRESS "+address+""+city+""+pincode);

        User updateduser = new User();
        Address arr= new Address();
        arr.setAddr(userAddress);
        arr.setCity(city);
        arr.setState(state);
        arr.setPincode(pincode);
        updateduser.setUserName(userName);
        updateduser.setUserPhone(userPhone);

        if(file== null)
        {
            user.setUserPic("contact.png");
        }
        else {
            String fileName; //= user.getUserPic().toString();
            String folderPath = new ClassPathResource("static/img/").getFile().getAbsolutePath();
            File folder = new File(folderPath);
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            fileName = UUID.randomUUID().toString() + fileExtension;
            user.setUserPic(fileName);
            System.out.println("Image is uploaded");


            try {
                File image = new File(folder, fileName);
                file.transferTo(image);
            } catch (Exception e) {
                System.out.println("Exception AA gri");
                e.printStackTrace();

            }
        }


        user.setUserName(updateduser.getUserName());
        user.setUserPhone(updateduser.getUserPhone());
    address.setCity(arr.getCity());
    address.setState(arr.getState());
    address.setPincode(arr.getPincode());
    address.setAddr(arr.getAddr());

        userRepository.save(user);
addressRepository.save(address);
        return ResponseEntity.ok("User Details Updated successfully!");

    }
























   /* @GetMapping(value = "/users/{id}/picture", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserProfileDto> getUserProfile(@PathVariable Long id) throws IOException {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);

        Path imagePath = new ClassPathResource("static/img/" + user.getUserPic()).getFile().toPath();
        byte[] imageBytes = Files.readAllBytes(imagePath);

        UserProfileDto userProfileDTO = new UserProfileDto(user.getUserName(), user.getEmail(), imageBytes);

        return ResponseEntity.ok().body(userProfileDTO);
    }
*/
    }



























        //return ResponseEntity.ok("Logout Successful");



