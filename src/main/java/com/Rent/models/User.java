package com.Rent.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private int id;
	private String userName;
	private String email;
	private String userPhone;
	private String password;
	private String userPic;




	@OneToMany(mappedBy= "category")
	private List<Product> products= new ArrayList<>();
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Address address;

	public User() {
	}


	public User(String userName, String email, String userPhone, String password, String userPic, List<Product> products, Address address) {
		this.userName = userName;
		this.email = email;
		this.userPhone = userPhone;
		this.password = password;
		this.userPic = userPic;
		this.products = products;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}


	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
