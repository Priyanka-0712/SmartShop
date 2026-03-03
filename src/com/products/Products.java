package com.products;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.connection.TestConnection;

public class Products {
	
//========================= Declaring Variables ========================================
	
	private int product_id;
	private String product_name;
	private String description;
	private double price;
	private int quantity;

//========================= Getters And Setters ========================================= 
	
	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
//============================= Constructors ===========================================
	
	public Products()
	{
		
	}
	
	public Products(int product_id, String product_name, double price, int quantity) {
	this.product_id = product_id;
	this.product_name = product_name;
	this.price = price;
	this.quantity = quantity;
	}
	
	public Products(String product_name,String description, double price, int quantity) {
		this.description=description;
		this.product_name = product_name;
		this.price = price;
		this.quantity = quantity;
		}

//================== Connection Object Creation =======================================

	Connection connection=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	CallableStatement callableStatement=null;
	Scanner scanner=new Scanner(System.in);
	

//=========================== Display All Products ====================================
		
		public void viewProductsList () throws SQLException
		{
			try {
				connection=TestConnection.getConnection();
				callableStatement=connection.prepareCall("{CALL view_all_products}");
				resultSet=callableStatement.executeQuery();
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
					System.out.println(resultSet.getInt(1)+"\t|"+resultSet.getString(2)+"\t|\r\n"
				+resultSet.getString(3)+"\t|"+resultSet.getDouble(4)+"\t|"+resultSet.getInt(5)+"\t|");
				}
				System.out.println("=====================================================================================");
			}
			catch (Exception e) {
					e.printStackTrace();
				}
			finally {
					connection.close();
					callableStatement.close();
				}
			}
		
//========================== Search Products by Name or Keyword ========================= 
		
		public void searchProduct()
		{
			try {
				connection=TestConnection.getConnection();
				preparedStatement=connection.prepareStatement("Select product_id as 'Product Id',\r\n"
						+ "product_name as 'Product Name',description as Description ,\r\n"
						+ "price as Price , quantity as Quantity from products where product_name like ?");
				System.out.println("Enter product name or keyword to search >> ");
				String search=scanner.nextLine().trim();
				preparedStatement.setString(1, "%"+search+"%");
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
					System.out.println(resultSet.getInt(1)+"\t|"+resultSet.getString(2)+"\t|\r\n"+
				resultSet.getString(3)+"\t|"+resultSet.getDouble(4)+"\t|"+resultSet.getInt(5)+"\t|");
				}
				System.out.println("=====================================================================================");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//========================================= End =========================================================================
}
