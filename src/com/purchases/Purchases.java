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

//============== view user purchase history =============================================
	
		public void userPurchaseHistory() throws SQLException
		{
			try {
				Users users=new Users();
				connection=TestConnection.getConnection();
				preparedStatement=connection.prepareStatement("select p.product_id as 'Product Id' ,\r\n"
						+ "p.product_name as 'Product Name', pu.quantity as Quantity ,\r\n"
						+ "pu.purchase_date as Date, pu.price_at_purchase as 'Price'\r\n" + 
						" from users u join purchases pu on u.user_id=pu.user_id join\r\n"
						+ "products p on pu.product_id=p.product_id where u.username=?;");
				
				System.out.println("Enter the Username to view their purchase history >> ");
				String username=scanner.nextLine().trim();
				username=users.getUsername();
				preparedStatement.setString(1, username);
				resultSet=preparedStatement.executeQuery();
				ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
				int columnCount=resultSetMetaData.getColumnCount();
				System.out.println("=====================================================================================");
				for(int i=1;i<=columnCount;i++)
				{
					System.out.print(resultSetMetaData.getColumnLabel(i)+"\t|");
				}
				System.out.println();
				System.out.println("=====================================================================================");
				while(resultSet.next())
				{
					System.out.println(resultSet.getInt("Product Id")+"\t|"+resultSet.getString("Product Name")+"\t|\r\n"
				+resultSet.getInt("Quantity")+"\t|"+resultSet.getDate("Date")+"\t|"+resultSet.getDouble("Price")+"\t|");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			}
		}
			
//============================================ End ==========================================================================
}
