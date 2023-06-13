package com.Rent.service;

import com.Rent.dto.ProductDto;
import com.Rent.models.Product;
import com.Rent.models.User;
import com.Rent.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductsByName(String name) {
        return productRepository.findByPnameContainingIgnoreCase(name);
    }

    public List<Product> getAvailableProductsByName(String productName) {
        return productRepository.findByPnameIgnoreCaseAndAvailableTrue(productName);
    }

    public List<Product> getProductByCategoryId(int id) {
        return productRepository.findBycategoryCategoryId(id);
    }
    public List<Product> getAvailableProductByCategoryId(int id) {
        return productRepository.findBycategoryCategoryIdAndAvailableTrue(id);
    }



    public ProductDto convertToProductDto(Product product)
    {
        ProductDto productDto= new ProductDto();
        productDto.setProduct(product);
        User user=product.getUser();
        productDto.setPowner(user.getUserName());
        return productDto;

    }
}
