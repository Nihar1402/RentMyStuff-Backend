package com.Rent.dto;

import com.Rent.models.Product;

public class   ProductDto {

    private Product product;
    private String Powner;

    public ProductDto() {
    }

    public ProductDto(Product product, String powner) {
        this.product = product;
        Powner = powner;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getPowner() {
        return Powner;
    }

    public void setPowner(String powner) {
        Powner = powner;
    }
}
