package com.Rent.models;


import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String addr;

    @Column
    private String city="Indore";

    @Column
    private String state;

    @Column
    private String pincode;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "address")
    private Order_Table order_table;
    public Address() {
    }


    public Address(int id, String addr, String city, String state, String pincode, User user) {
        this.id = id;
        this.addr = addr;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Order_Table getOrder_table() {
        return order_table;
    }

    public void setOrder_table(Order_Table order_table) {
        this.order_table = order_table;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // constructors, getters, and setters
}

