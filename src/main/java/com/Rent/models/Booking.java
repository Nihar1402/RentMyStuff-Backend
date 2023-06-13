package com.Rent.models;




import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "renterId")
    private User user;



    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date endDate;


    private int quantity;
    @Column(columnDefinition = "varchar(25) default 'Pending'")
    private String status;

    @JsonIgnore
    @OneToOne
    private Order_Table orderTable;
     private int ownerId;

    public Booking() {
    }

    public Booking(Date startDate, Date endDate, int quantity) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.quantity = quantity;
    }

    public Booking(Product product, User user, Date startDate, Date endDate, int quantity, String status, Order_Table orderTable) {
        this.product = product;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quantity = quantity;
        this.status = status;
        this.orderTable = orderTable;
    }

    public Booking(Product product, User user, Date startDate, Date endDate, int quantity, String status, Order_Table orderTable, int ownerId) {
        this.product = product;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quantity = quantity;
        this.status = status;
        this.orderTable = orderTable;
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Order_Table getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(Order_Table orderTable) {
        this.orderTable = orderTable;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
    /*
    public Booking(int id, String renterName, Product product, User user, Date startDate, Date endDate, int quantity, String status, Order_Table orderTable) {
        this.id = id;
        this.renterName = renterName;
        this.product = product;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quantity = quantity;
        this.status = status;
        this.orderTable = orderTable;
    }

    public Booking(String renterName, Product product, User user, Date startDate, Date endDate, int quantity, String status, Order_Table orderTable) {
        this.renterName = renterName;
        this.product = product;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quantity = quantity;
        this.status = status;
        this.orderTable = orderTable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Order_Table getOrder() {
        return orderTable;
    }

    public void setOrder(Order_Table orderTable) {
        this.orderTable = orderTable;
    }*/
}