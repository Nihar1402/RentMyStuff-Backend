package com.Rent.dto;

import java.util.Date;

public class BookingDto {

    private int bid;
    private String renterName;
    private String ownerName;
    private  int ProductId;
    private String startDate;
    private String endDate;
    private int quantity;
    private String status;
    private int orderId;

    public BookingDto() {
    }

    public BookingDto(String renterName, String ownerName, int productId, String startDate, String endDate, int quantity, String status, int orderId) {
        this.renterName = renterName;
        this.ownerName = ownerName;
        ProductId = productId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quantity = quantity;
        this.status = status;
        this.orderId = orderId;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }

    public String getOwnerName(String userName) {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
