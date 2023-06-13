package com.Rent.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity

public class Category {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int categoryId;
	private String categoryTitle;

	@OneToMany(mappedBy= "category")
	private List<Product> products= new ArrayList<>();
	public Category() {
		super();
	}

	public Category(int categoryId, String categoryTitle, List<Product> products) {
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.products = products;
	}

	public Category(String categoryTitle, List<Product> products) {
		this.categoryTitle = categoryTitle;
		this.products = products;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
