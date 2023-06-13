package com.Rent.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity

public class Order_Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bookingId", nullable = false)
    private Booking booking;

    @Column(name = "num_of_days", nullable = false)
    private int numOfDays;


    @Column(name = "service_charge", nullable = false)
    private BigDecimal serviceCharge;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Lob
    @Column(length = 500000)
    private byte[] pdfname;

    @JsonIgnore
    @OneToOne
    private Transaction transactions;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public Order_Table() {
    }

    public Order_Table(Booking booking, int numOfDays, BigDecimal serviceCharge, BigDecimal total, byte[] pdfname, Transaction transactions, Address address) {
        this.booking = booking;
        this.numOfDays = numOfDays;
        this.serviceCharge = serviceCharge;
        this.total = total;
        this.pdfname = pdfname;
        this.transactions = transactions;
        this.address = address;
    }

    public Order_Table(int id, Booking booking, int numOfDays, BigDecimal serviceCharge, BigDecimal total, byte[] pdfname, Transaction transactions, Address address) {
        this.id = id;
        this.booking = booking;
        this.numOfDays = numOfDays;
        this.serviceCharge = serviceCharge;
        this.total = total;
        this.pdfname = pdfname;
        this.transactions = transactions;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
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


    public byte[] getPdfname() {
        return pdfname;
    }

    public void setPdfname(byte[] pdfname) {
        this.pdfname = pdfname;
    }

    public Transaction getTransactions() {
        return transactions;
    }

    public void setTransactions(Transaction transactions) {
        this.transactions = transactions;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}