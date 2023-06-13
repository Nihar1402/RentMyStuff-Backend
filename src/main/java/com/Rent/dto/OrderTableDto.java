package com.Rent.dto;

import java.math.BigDecimal;
import java.util.Date;

public class OrderTableDto {


    private int bookingId;
    private int numOfDays;
    private BigDecimal pricePerDay;
    private Date startDate;
    private Date endDate;

    private BigDecimal securityDeposit;

    private BigDecimal serviceCharge;

    private BigDecimal total;

    private String ownerName;
    private String renterName;

    private String productName;

    private byte[] pdfname;

    private int quantity;

    private int TransactionId;
    private  int orderId;


    public OrderTableDto() {
    }

    public OrderTableDto(int bookingId, int numOfDays, BigDecimal pricePerDay, Date startDate, Date endDate, BigDecimal securityDeposit, BigDecimal serviceCharge, BigDecimal total, String ownerName, String renterName, String productName, byte[] pdfname, int quantity, int transactionId, int orderId) {
        this.bookingId = bookingId;
        this.numOfDays = numOfDays;
        this.pricePerDay = pricePerDay;
        this.startDate = startDate;
        this.endDate = endDate;
        this.securityDeposit = securityDeposit;
        this.serviceCharge = serviceCharge;
        this.total = total;
        this.ownerName = ownerName;
        this.renterName = renterName;
        this.productName = productName;
        this.pdfname = pdfname;
        this.quantity = quantity;
        TransactionId = transactionId;
        this.orderId = orderId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public BigDecimal getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(BigDecimal securityDeposit) {
        this.securityDeposit = securityDeposit;
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public byte[] getPdfname() {
        return pdfname;
    }

    public void setPdfname(byte[] pdfname) {
        this.pdfname = pdfname;
    }

    public int getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(int transactionId) {
        TransactionId = transactionId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
