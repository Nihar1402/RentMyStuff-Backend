
package com.Rent.Controller;

import com.Rent.dto.*;
import com.Rent.models.*;
import com.Rent.repository.*;
import com.Rent.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000" ,allowedHeaders = "*" ,allowCredentials = "true")
@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    OrderService or;
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/addproduct")
    public ResponseEntity<String> addProduct(@RequestParam(value = "pPhoto" ,required = false) MultipartFile file,
                                             @RequestParam String pName,
                                             @RequestParam String pDesc,
                                             @RequestParam BigDecimal priceperday,
                                             @RequestParam BigDecimal securityDeposite,
                                             @RequestParam int Quantity,
                                             @RequestParam String Cate,
                                              HttpSession session) throws IOException {
        Product product = new Product();
        product.setPname(pName);
        product.setpDesc(pDesc);
        product.setPriceperday(priceperday);
        product.setSecurityDeposite(securityDeposite);
        product.setQuantity(Quantity);
        product.setAvailable(true);
        Category cat= categoryRepository.findByCategoryTitle(Cate);
       product.setCategory(cat);
       product.setpPhoto(file.getBytes());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        user.getProducts().add(product);
        product.setUser(user);
        this.productRepository.save(product);
        return ResponseEntity.ok("Product Added successfully!");
    }


    @GetMapping("/mylisting")
    public List<Product> getProductByRid()
   {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String email = authentication.getName();
        User user = userRepository.findByEmail(email);
System.out.println(email);
       return productRepository.findByUser_Id(user.getId());

    }
    @GetMapping("/mybookings")
    public  ResponseEntity<List<BookingProductDto>> getBookingsAndProducts()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        int id=user.getId();
        System.out.println(email);
        List<BookingProductDto> result = new ArrayList<>();
        List<Booking> bookings = bookingRepository.findByUserId(id);
        for (Booking booking : bookings) {
            System.out.println(booking);
            Product product = booking.getProduct();
            BookingDto bookingDto = or.convertToBookingDto(booking);
          System.out.println(bookingDto.getStartDate());
            result.add(new BookingProductDto(bookingDto , product));
        }
       // System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/myrentals")
    public  ResponseEntity<List<OrderProductDto>> getOrderAndProducts() {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        int id=user.getId();
        List<OrderProductDto> result = new ArrayList<>();
        List<Order_Table> ord= new ArrayList<>();
        List<Booking> bookings = bookingRepository.findByUserId(id);
        System.out.println(bookings);
        for (Booking booking : bookings) {
            if(booking.getStatus().equals("PAID")) {


                int oid = booking.getOrderTable().getId();

                ord.add(orderRepository.findById(oid));

            }

        }

          for (Order_Table ch : ord)
          {
              System.out.println(ch.getId());
              try
              {
                  System.out.println();
                  System.out.println(ch.getTransactions().getStatus().equals(("paid")));
          if(ch.getTransactions().getStatus().equals(("paid"))) {

              Product product = ch.getBooking().getProduct();
              System.out.println(product);
              OrderTableDto orderProductDto = or.convertToOrderDto(ch);
              result.add(new OrderProductDto(orderProductDto, product));
          }
          }catch(Exception e)
                  {
                  e.printStackTrace();
                  }

          }
          return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/bookingRequest")
    public ResponseEntity<List<BookingProductDto>> getMyBookingRequest()
        {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            User user = userRepository.findByEmail(email);
            int id=user.getId();
            if(user.getProducts()==null)

            {
                System.out.println("You have not Listed any product");
                return ResponseEntity.badRequest().body(Collections.emptyList());

            }

            List<Booking> bookings = bookingRepository.findByOwnerId(id);
            List<BookingProductDto> result = new ArrayList<>();



            for (Booking booking : bookings) {
                Product product = booking.getProduct();
                BookingDto bookingDto = or.convertToBookingDto(booking);
//                System.out.println("loop ka andar aa gya");
                result.add(new BookingProductDto(bookingDto , product));
            }
           // System.out.println(result);
            return new ResponseEntity<>(result, HttpStatus.OK);

        }

        @PostMapping("/update-product-details/{productId}")
    public ResponseEntity<String> addProduct(@PathVariable int productId, @RequestParam(value = "pPhoto" ,required = false) MultipartFile file,
                                             @RequestParam String pName,
                                             @RequestParam String pDesc,
                                             @RequestParam BigDecimal priceperday,
                                             @RequestParam BigDecimal securityDeposite,
                                             @RequestParam int Quantity,
                                             @RequestParam String Category,
                                             Principal principal, HttpSession session) throws IOException {
        Product productupdater = new Product();
            Product product = productRepository.findById(productId).get();
        productupdater.setPname(pName);
        productupdater.setpDesc(pDesc);
        productupdater.setPriceperday(priceperday);
        productupdater.setSecurityDeposite(securityDeposite);
        productupdater.setQuantity(Quantity);
        Category cat= categoryRepository.findByCategoryTitle(Category);
        productupdater.setCategory(cat);
        if(file!=null) {
            productupdater.setpPhoto(file.getBytes());
            product.setpPhoto((productupdater.getpPhoto()));

        }


        product.setPname(productupdater.getPname());
        product.setpDesc(productupdater.getpDesc());
        product.setPriceperday(productupdater.getPriceperday());
        product.setSecurityDeposite(productupdater.getSecurityDeposite());
        product.setQuantity(productupdater.getQuantity());
        product.setCategory(productupdater.getCategory());


        this.productRepository.save(product);
        return ResponseEntity.ok("Product Details Updated");
    }

    @DeleteMapping("/deleteproduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        System.out.println(id);
        int tem=Integer.parseInt(id);

        Optional<Product> optionalProduct = productRepository.findById(tem);
        if (!optionalProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");

        }
        Product product = optionalProduct.get();
        if (product.isAvailable()) {
            productRepository.delete(product);
            return ResponseEntity.ok("Product deleted successfully.");
        } else {
            return ResponseEntity.badRequest().body("Cannot delete product as it is currently rented out.");
        }
    }

}



