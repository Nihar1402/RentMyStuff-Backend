package com.Rent.dto;

import com.Rent.models.Product;

public class OrderProductDto {


    private OrderTableDto orderTableDto;

    private Product product;

    public OrderProductDto() {
    }

    public OrderProductDto(OrderTableDto orderTableDto, Product product) {
        this.orderTableDto = orderTableDto;
        this.product = product;
    }

    public OrderTableDto getOrderTableDto() {
        return orderTableDto;
    }

    public void setOrderTableDto(OrderTableDto orderTableDto) {
        this.orderTableDto = orderTableDto;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
