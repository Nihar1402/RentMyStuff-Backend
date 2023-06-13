package com.Rent.Controller;

import com.Rent.dto.ProductDto;
import com.Rent.models.Category;
import com.Rent.models.Product;
import com.Rent.repository.CategoryRepository;
import com.Rent.repository.ProductRepository;
import com.Rent.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000" ,allowedHeaders = "*" ,allowCredentials = "true")
@RestController

public class ProductDetails {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable int id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            ProductDto p= productService.convertToProductDto(product.get());
            return ResponseEntity.ok(p);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/products/all")
    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }
    @GetMapping("/products/available")
    public List<Product> getAvailableProducts()
    {
        return productRepository.findByAvailableTrue();
    }
    @GetMapping("/search/{productName}")
    public ResponseEntity<List<Product>> getProductsByName(@PathVariable String productName) {

        List<Product> products = productService.getProductsByName(productName);

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();

        }

        List<Product> matchedProducts = new ArrayList<>();
        Pattern pattern = Pattern.compile(productName, Pattern.CASE_INSENSITIVE);
        for (Product product : products) {
            Matcher matcher = pattern.matcher(product.getPname());
            if (matcher.find()) {
                matchedProducts.add(product);
            }
        }

        if (matchedProducts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(matchedProducts);


        //return ResponseEntity.ok(products);
    }
    @GetMapping("/search/available/{productName}")
    public ResponseEntity<List<Product>> getAvailableProductsByName(@PathVariable String productName) {

        Pattern pattern = Pattern.compile(productName, Pattern.CASE_INSENSITIVE);

        List<Product> availableProducts = productService.getAvailableProductsByName(productName);

        if (availableProducts.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            // return  ResponseEntity.ok();
           return ResponseEntity.ok(availableProducts);
        }
    }
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Product>> getProductsByCategoryIgnoreCase(@PathVariable String categoryName) {

        Category category= categoryRepository.findByCategoryTitle(categoryName);
        int cid= category.getCategoryId();
        List<Product> products = productService.getProductByCategoryId(cid);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category/available/{categoryName}")
    public ResponseEntity<List<Product>> getAvailableProductsByCategory(@PathVariable String categoryName) {

        Category category= categoryRepository.findByCategoryTitle(categoryName);
        int cid= category.getCategoryId();
        List<Product> products = productService.getAvailableProductByCategoryId(cid);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }







}







