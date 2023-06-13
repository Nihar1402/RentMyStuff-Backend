package com.Rent.dto;

import com.Rent.models.Product;

public class BookingProductDto {
    private BookingDto bookingDto;

    private Product product;

    public BookingProductDto() {
    }

    public BookingProductDto(BookingDto bookingDto, Product product) {
        this.bookingDto = bookingDto;
        this.product = product;
    }


    public BookingDto getBookingDto() {
        return bookingDto;
    }

    public void setBookingDto(BookingDto bookingDto) {
        this.bookingDto = bookingDto;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
