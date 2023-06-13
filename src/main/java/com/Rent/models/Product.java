package com.Rent.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String pname;
	@Column(length=5000)
	private String pDesc;
	@Lob
	@Column(length=500000)
	private byte[] pPhoto;
	private BigDecimal priceperday;
	private BigDecimal securityDeposite;
	private int Quantity;
	@Column(columnDefinition = "boolean default false")
	private boolean available;
	@JsonIgnore
	@ManyToOne
	private Category category;
@JsonIgnore

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	public Product() {
		super();
	}

	public Product(int id, String pname, String pDesc, byte[] pPhoto, BigDecimal priceperday, BigDecimal securityDeposite, int quantity, boolean available, Category category, User user) {
		this.id = id;
		this.pname = pname;
		this.pDesc = pDesc;
		this.pPhoto = pPhoto;
		this.priceperday = priceperday;
		this.securityDeposite = securityDeposite;
		Quantity = quantity;
		this.available = available;
		this.category = category;
		this.user = user;
	}

	public Product(String pname, String pDesc, byte[] pPhoto, BigDecimal priceperday, BigDecimal securityDeposite, int quantity, boolean available, Category category, User user) {
		this.pname = pname;
		this.pDesc = pDesc;
		this.pPhoto = pPhoto;
		this.priceperday = priceperday;
		this.securityDeposite = securityDeposite;
		Quantity = quantity;
		this.available = available;
		this.category = category;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getpDesc() {
		return pDesc;
	}

	public void setpDesc(String pDesc) {
		this.pDesc = pDesc;
	}


	public byte[] getpPhoto() {
		return pPhoto;
	}

	public void setpPhoto(byte[] pPhoto) {
		this.pPhoto = pPhoto;
	}

	public BigDecimal getPriceperday() {
		return priceperday;
	}

	public void setPriceperday(BigDecimal priceperday) {
		this.priceperday = priceperday;
	}

	public BigDecimal getSecurityDeposite() {
		return this.securityDeposite;
	}

	public void setSecurityDeposite(BigDecimal securityDeposite) {
		this.securityDeposite = securityDeposite;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}