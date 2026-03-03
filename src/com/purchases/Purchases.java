package com.purchases;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

import com.connection.TestConnection;
import com.products.Products;
import com.users.Users;

public class Purchases {
	
//====================== Declaring Variables ===========================================
	
	private int purchase_id;
	private double price_at_purchase;
	private int quantity;
	private Users user;
	private Products product;
	Date purchase_date;
	
//======================= Setters Getters =======================================================
	
	public Date getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}
	public int getPurchase_id() {
		return purchase_id;
	}
	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}

	public double getPrice_at_purchase() {
		return price_at_purchase;
	}
	public void setPrice_at_purchase(double price_at_purchase) {
		this.price_at_purchase = price_at_purchase;
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
	
//======================= Constructors =================================================

	public Purchases()
	{
		
	}
	
	public Purchases(int purchase_id, double price_at_purchase, int quantity, Users user, Products product,
			Date purchase_date) {
		super();
		this.purchase_id = purchase_id;
		this.price_at_purchase = price_at_purchase;
		this.quantity = quantity;
		this.user = user;
		this.product = product;
		this.purchase_date = purchase_date;
	}

	
//====================== creating connection objects ===================================
	
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	Scanner scanner=new Scanner(System.in);
	

	
//============================================ End ==========================================================================
}
