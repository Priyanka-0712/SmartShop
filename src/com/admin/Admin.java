package com.admin;

import java.sql.SQLException;
import java.util.Scanner;

import com.products.Products;
import com.purchases.Purchases;

public class Admin {
	
//============================= Admin Operation =============================================================
	
		Scanner scanner=new Scanner(System.in);
		int input;
		
		public void adminOperation() throws SQLException
		{
			
				Products products=new Products();
				Purchases purchases=new Purchases();
				
			do {
				
				System.out.println("===========================================");
				System.out.println(" Please choose an option: ");
				System.out.println(" 1. Add New Product  ");
				System.out.println(" 2. View Product Stock");
				System.out.println(" 3. View Registered Users ");
				System.out.println(" 4. View User Purchase History ");
				System.out.println(" 5. Update Product Details  ");
				System.out.println(" 6. Delete Product from Inventory ");
				System.out.println(" 7. Log Out");
				System.out.println("===========================================");
				System.out.println("Enter your choice >> ");
				int choice=Integer.parseInt(scanner.nextLine().trim());
				
				switch (choice) {
				case 1:products.addProduct();
					break;

				case 2:products.getProductStock();
					break;
					
				case 3:products.getRegisteredUsers();
					break;
					
				case 4:purchases.userPurchaseHistory();
					break;
					
				case 5:products.updateProductDetails();
					break;
					
				case 6:products.deleteProduct();
					break;
				
				default:
					break;
				}
			}while(input!=7);
		}
		
//============================================ End ==========================================================================

}
