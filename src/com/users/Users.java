package com.users;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

import com.connections.TestConnection;
import com.customexception.MyException;

public class Users {

//====================== Declaring Variables ========================
	
		private int user_id;
		private String first_name;
		private String last_name;
		private String username;
		private String password;
		private String city;
		private String email;
		private String mobile;
		private String role;

//========================= Constructors ==============================

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
		
//======================= Connection Object Creation ======================================================

		Connection connection=null;
		PreparedStatement preparedStatement=null;
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Scanner scanner=new Scanner(System.in);
		
//======================== User Login First ===========================================================
	
		public static void loginFirst()
		{
			try
			{
				throw new MyException("Please Login First");
			}
			catch (MyException e) {
				System.out.println("================================");
				System.out.println("     "+e.getMessage());
			}
		}
		
//=======================  User Purchase History ==========================================================

		public void getUserPurchaseHistory() throws SQLException
		{
			usersLogin();
			System.out.println("Your Order History Is ");
			connection=TestConnection.getConnection();
			preparedStatement=connection.prepareStatement("SELECT\r\n" + 
					"p.purchase_date as Date,\r\n" + 
					"pr.product_name 'Product Name',\r\n" + 
					"p.quantity As Quantity,\r\n" + 
					"p.price_at_purchase AS 'Purchase Price',\r\n" + 
					"(p.quantity*p.price_at_purchase) as Total\r\n" + 
					"from purchases p join products pr \r\n" + 
					"on p.product_id=pr.product_id \r\n" + 
					"where p.user_id=?");
			
			preparedStatement.setInt(1, user_id);
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
				System.out.print(resultSet.getDate("Date")+"\t|"+resultSet.getString("Product Name")
				+"\t|"+resultSet.getString("Quantity")+"\t|"+resultSet.getString("Purchase Price")
				+"\t|"+resultSet.getDouble("Total"));
			}

		}		
		
//========================================= End =========================================================================
}
