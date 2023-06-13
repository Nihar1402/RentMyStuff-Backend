package com.Rent.Controller;

import com.Rent.models.User;
import com.Rent.repository.UserRepository;
import com.Rent.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Random;


@CrossOrigin(origins = "http://localhost:3000" ,allowedHeaders = "*" ,allowCredentials = "true")
@RestController

public class ForgotController {
    Random random = new Random(1000);
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepsitory;

    @Autowired
    private BCryptPasswordEncoder bcrypt;


int myOtp;
String xmail= new String();
    @PostMapping("/sendotp")
    public ResponseEntity<String> Sendotp(@RequestParam("email") String email, HttpSession session) {
        //String email=(String)session.getAttribute("email");

        User user = this.userRepsitory.getUserByUserName(email);
        xmail=email;
        if (user == null) {

            return ResponseEntity.badRequest().body("User Name Does not exist");

        } else {

            System.out.println("EMAIL " + email);
            int otp = random.nextInt(999999);
            myOtp=otp;
            System.out.println("OTP " + otp);
            String subject = "OTP From RentMyStuff";
            String message = ""
                    + "<div style='border:1px solid #e2e2e2; padding:20px'>"
                    + "<h1>"
                    + "OTP is "
                    + "<b>" + otp
                    + "</n>"
                    + "</h1> "
                    + "</div>";
            String to = email;
            boolean flag = this.emailService.sendEmail(subject, message, to);

            if (flag) {

                //session.setAttribute("myotp", otp);
                //  session.setAttribute("email", email);

                return new ResponseEntity<>("OTP sent successfully!", HttpStatus.OK);
            } else {


                return new ResponseEntity<>("Invalid email address", HttpStatus.BAD_REQUEST);

                //return "forgot_email_form";
            }

        }
    }



    @PostMapping("/verifyotp")
    public ResponseEntity<String> verifyOTP(@RequestParam("ot") String ot, HttpSession session )
    {
      //  int myOtp=(int)session.getAttribute("myotp");
        int otp= Integer.parseInt(ot);

       System.out.println("User OTP "+otp);
       System.out.println("Our OTP "+myOtp);


       // String email=(String)session.getAttribute("xmail");
        if(myOtp==otp)
        {
            return ResponseEntity.ok().build();
        }

        else
        {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }
    }


    //change password
    @PostMapping("/changepassword")
    public ResponseEntity<String> changePassword(@RequestParam("newpassword") String newpassword, HttpSession session)
    {
       // String email=(String)session.getAttribute("email");
        User user = this.userRepsitory.getUserByUserName(xmail);
        user.setPassword(this.bcrypt.encode(newpassword));
        this.userRepsitory.save(user);
        return ResponseEntity.ok("Password changed successfully.");

    }


}