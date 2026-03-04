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
	
//=========================== Add Products =============================================
	
		public void addProduct() throws SQLException
		{
			List<Products> productsList=new ArrayList<Products>();
			while(true)
			{
				System.out.println("Enter Product Name >> ");
				product_name=scanner.nextLine().trim();
				System.out.println("Enter Product Description >> ");
				description=scanner.nextLine().trim();
				System.out.println("Enter Product Price >> ");
				price=Double.parseDouble(scanner.nextLine().trim());
				System.out.println("Enter Product Quantity >> ");
				quantity=Integer.parseInt(scanner.nextLine().trim());
				productsList.add(new Products(product_name, description, price, quantity));
				System.out.println("Do you Want To add Another Product yes//no ");
				String choice=scanner.nextLine();
				if(choice.equalsIgnoreCase("no"))
				{
					break;
				}
			}
			try {
				connection=TestConnection.getConnection();
				connection.prepareStatement("insert into products\r\n"
						+ "(product_name,description,price,quantity)values(?,?,?,?)");
				for(Products products:productsList)
				{
					preparedStatement.setString(1, products.getProduct_name());
					preparedStatement.setString(2, products.getDescription());
					preparedStatement.setDouble(3, products.getPrice());
					preparedStatement.setInt(4, products.getQuantity());
					preparedStatement.addBatch();
				}
				preparedStatement.executeBatch();
				System.out.println("Saving product to the database... ");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				connection.close();
				preparedStatement.close();
			}
			
		}
		
//=================== View Product Stock ================================================

		public void getProductStock() throws SQLException
		{
			try {
				System.out.println("Enter Product ID to check stock >> ");
				product_id=Integer.parseInt(scanner.nextLine().trim());
				connection=TestConnection.getConnection();
				preparedStatement=connection.prepareStatement
						("select quantity from products where product_id=?");
				preparedStatement.setInt(1, product_id);
				resultSet=preparedStatement.executeQuery();
				while(resultSet.next())
				{
					quantity=resultSet.getInt("quantity");
				}
				System.out.println("Available Quantity >> "+quantity);
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
		
//=================== View Registered Users ==========================================
		
		public void getRegisteredUsers() throws SQLException
		{
			
			try {
				connection=TestConnection.getConnection();
				callableStatement=connection.prepareCall("{CALL view_registered_users()}");
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
					System.out.println(resultSet.getInt(1)+"\t|"+resultSet.getInt(2)+"\t|\r\n"
				+resultSet.getInt(3)+"\t|"+resultSet.getInt(4)+"\t|"+resultSet.getInt(5)+"\t|");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				connection.close();
				callableStatement.close();
				resultSet.close();
			}
		}
		
//========================== Update Product Details ===================================================================	 
		
		public void updateProductDetails() throws SQLException
		{
			try {
				connection=TestConnection.getConnection();
				System.out.println("Enter the Product ID to update >> ");
				product_id=Integer.parseInt(scanner.nextLine().trim());
				preparedStatement=connection.prepareStatement
						("select * from products where product_id=?");
				preparedStatement.setInt(1, product_id);
				preparedStatement.executeQuery();
				while(resultSet.next())
				{
					product_name=resultSet.getString("product_name");
					description=resultSet.getString("description");
					price=resultSet.getDouble("price");
					quantity=resultSet.getInt("quantity");
				}
				System.out.println("Select field to update: ");
				System.out.println("1. Product Name ");
				System.out.println("2. Description ");
				System.out.println("3. Price ");
				System.out.println("4. Quantity ");
				System.out.println();
				System.out.println("Enter your choice >> ");
				int choice=Integer.parseInt(scanner.nextLine().trim());
				switch (choice) {
				case 1:System.out.println("Enter new value >> ");
						product_name=scanner.nextLine().trim();
						preparedStatement=connection.prepareStatement
								("update products set product_name=? where product_id=?");
						preparedStatement.setString(1, product_name);
						preparedStatement.setInt(2, product_id);
					break;

				case 2:System.out.println("Enter new value >> ");
						description=scanner.nextLine().trim();
						preparedStatement=connection.prepareStatement
								("update products set description=? where product_id=?");
						preparedStatement.setString(1, description);
						preparedStatement.setInt(2, product_id);
					break;
					
				case 3:System.out.println("Enter new value >> ");
						price=Double.parseDouble(scanner.nextLine().trim());
						preparedStatement=connection.prepareStatement
								("update products set price=? where product_id=?");
						preparedStatement.setDouble(1, price);
						preparedStatement.setInt(2, product_id);
					break;
					
				case 4:System.out.println("Enter new value >> ");
						quantity=Integer.parseInt(scanner.nextLine().trim());
						preparedStatement=connection.prepareStatement
								("update products set quantity=? where product_id=?");
						preparedStatement.setInt(1, quantity);
						preparedStatement.setInt(2, product_id);
					break;
				default:
					break;
				}
				preparedStatement.executeUpdate();
				System.out.println("Updating product in database... ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			}
			
		}
		
//====================== Delete Product From Inventory =========================================================
		
		public void deleteProduct() throws SQLException
		{
			try {
				System.out.println("Enter the Product ID to delete >> ");
				product_id=Integer.parseInt(scanner.nextLine().trim());
				connection=TestConnection.getConnection();
				preparedStatement=connection.prepareStatement
						("delete from products where product_id=?");
				preparedStatement.setInt(1, product_id);
				System.out.println("Are you sure you want to delete this product? (Yes/No) >> ");
				String choice=scanner.nextLine().trim();
				{
					if(choice.equalsIgnoreCase("no"))
					{
						return;
					}
				}
				preparedStatement.executeUpdate();
				System.out.println("Deleting product from database... ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				connection.close();
				preparedStatement.close();
			}
		}
		


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
