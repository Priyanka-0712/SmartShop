package com.users;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.admin.Admin;
import com.connection.TestConnection;
import com.customexception.MyException;
import com.products.Products;

public class Users {
	
//====================== Declaring Variables ===========================================
	
	private int user_id;
	private String first_name;
	private String last_name;
	private String username;
	private String password;
	private String city;
	private String email;
	private String mobile;
	private String role;

//========================= Constructors ===============================================

	public Users()
	{
		
	}
	
	public Users(int user_id, String first_name, String last_name, String username, String password, String city,
			String email, String mobile, String role) {
		super();
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.city = city;
		this.email = email;
		this.mobile = mobile;
		this.role = role;
	}
	
//===================== Getters Setters ==================================

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
//================== Connection Object Creation =======================================

		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		CallableStatement callableStatement=null;
		Scanner scanner=new Scanner(System.in);

//========================== User Login ===============================================
	
	public void usersLogin() throws SQLException
	{
		connection=TestConnection.getConnection();
		preparedStatement=connection.prepareStatement("select * from users"
				+ " where username=? and password=?");
		int i=3;
		while(i!=0)
		{
		System.out.println("Enter Your Username ");
		username=scanner.nextLine();
		System.out.println("Enter Your Password");
		password=scanner.nextLine();
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		resultSet=preparedStatement.executeQuery();
		{
			if(resultSet.next())
		{
				
			if(username.equals(resultSet.getString("username"))&&
					password.equals(resultSet.getString("password")));
			{
				System.out.println("====================================================");
				System.out.println("Login SuccessFull Welcome "+
			resultSet.getString(2)+" "+resultSet.getString(3)+" !");
				
				user_id=resultSet.getInt(1);
				role=resultSet.getString(9);
				if(role.equals("admin"))
				{
					Admin admin=new Admin();
					admin.adminOperation();
				}
				else if(role.equals("user"))
				{
					userOperation();
				}
			}
			break;
		}
			else
		{
			System.out.println("=============================================");
			System.out.println("Please Enter Correct Username or Password ");
			if(i==0) {
				System.out.println("Login Failed  No Attempts Left ");
				return;
			}
			else if(i==1)
			{
				System.out.println("Last Attempt Remaining");
			}else
			{
			System.out.println("Attempt Left "+i);
			}
			i--;
		}
			
		}	
		
	  }
		return;
	}

//======================   User Operation    ===========================================
	
	public void userOperation() throws SQLException
	{
				
		Products products=new Products();
		products.viewProductsList();
		connection=TestConnection.getConnection();
		preparedStatement=connection.prepareStatement("select product_id,\r\n"
				+ "product_name,price,quantity from products");
		resultSet=preparedStatement.executeQuery();
		List<Products> list=new ArrayList<Products>();
		
		while(resultSet.next())
		{
			list.add(new Products(resultSet.getInt("product_id"), resultSet.getString("product_name"), resultSet.getDouble("price"), resultSet.getInt("quantity")));
		}
		
		preparedStatement.close();
		resultSet.close();
		
		System.out.println("Enter the Product ID to purchase >> ");
		int id=Integer.parseInt(scanner.nextLine().trim());
		System.out.println("Enter the Quantity >> ");
		int quant=Integer.parseInt(scanner.nextLine().trim());
		System.out.println("Checking stock availability...");
		if(quant<products.getQuantity())
		{
			System.out.println(" Product is not available ");
			return;
		}
		System.out.println("Adding product to your cart and saving purchase in database... ");
		preparedStatement=connection.prepareStatement("update products set quantity=quantity-? where product_id=? and quantity>=?");
		preparedStatement.setInt(1, quant);
		preparedStatement.setInt(2, id);
		preparedStatement.setInt(3, quant);
		int rows=preparedStatement.executeUpdate();
		if(rows>0)
		{
			System.out.println("Stock Updates Succesfully in Inventory");
		}
		else
		{
			System.out.println("Not Enough Stock Available");
		}
		
		boolean found=false;
		List<Products> cart=new ArrayList<Products>();
		Products selectedProduct=null;
		for(Products p:list)
		{
			if(p.getProduct_id()==id)
			{
				selectedProduct=p;
				break;
			}
		}
		for(Products p:cart)
		{
			if(p.getProduct_id()==id)
			{
				p.setQuantity(p.getQuantity()+quant);
				found=true;
				break;
			}
		}
		if(!found)
		{
			Products cartProducts=new Products(selectedProduct.getProduct_id(),selectedProduct.getProduct_name(),selectedProduct.getPrice(),quant);
			cart.add(cartProducts);
		}
		double totalAmount=0;
		System.out.printf("%-20s | %-10s | %-10s | %-10s\n","Product Name" , "Quantity" , "Price" , "Subtotal");
		System.out.println("===================================================================================");
		for(Products p:cart)
		{
			double subtotal=p.getPrice()*p.getQuantity();
			totalAmount+=subtotal;
			System.out.printf("%-20s | %-10d | %-10.2f | %-10.2f\n",p.getProduct_name(),p.getQuantity(),p.getPrice(),subtotal);
		}
		System.out.println("===================================================================================");
		System.out.printf("  Total Amount: %10.2f\n"+totalAmount);
		
		connection.close();
		preparedStatement.close();
		
		
	}
		
//========================================= End =========================================================================
}
