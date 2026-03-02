package com.purchases;

import com.products.Products;
import com.users.Users;

public class Purchases {

	private int purchase_id;
	private int quantity;
	private Users user;
	private Products product;
	
	public int getPurchase_id() {
		return purchase_id;
	}
	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}
	
	public Purchases()
	{
		
	}
	
	public Purchases(int purchase_id, int quantity, Users user, Products product) {
		super();
		this.purchase_id = purchase_id;
		this.quantity = quantity;
		this.user = user;
		this.product = product;
	}
	
	
}
