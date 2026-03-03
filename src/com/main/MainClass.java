package com.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.products.Products;
import com.users.Users;

public class MainClass {

	public static void main(String[] args) throws SQLException {
		Scanner scanner=new Scanner(System.in);
		int input =0;
		Users users=new Users();
		Products products=new Products();
	do
	{
		System.out.println("===========================================");
		System.out.println(" Welcome to E-Commerce Console Application ");
		System.out.println("===========================================");
		System.out.println(" Please choose an option: ");
		System.out.println(" 1. Register ");
		System.out.println(" 2. Login");
		System.out.println(" 3. View Products ");
		System.out.println(" 4. Search Product ");
		System.out.println(" 5. Add to Cart ");
		System.out.println(" 6. View Cart ");
		System.out.println(" 7. View Purchase History ");
		System.out.println(" 8. Exit");
		System.out.println("===========================================");
		System.out.println("Enter your choice >> ");
		try
		{
			input=Integer.parseInt(scanner.nextLine());
		}
		catch (Exception e) {
			System.out.println("=======================================");
			System.out.println("  Invalid Input Please try Again");
		}
		if(input==8)
		{
			System.out.println("=======================================");
			System.out.println("          Application Stopped          ");
			System.out.println("=======================================");
		}
		
		switch (input) {
		case 1: users.userRegistration();
			break;
			
		case 2: users.usersLogin();
			break;
			
		case 3:products.viewProductsList();
			break;
			
		case 4:products.searchProduct();
			break;
			
		case 5: Users.loginFirst();
			break;
			
		case 6: Users.loginFirst();
			break;
			
		case 7:users.getUserPurchaseHistory();
			break;
			
		default:
			break;
		}
		
	}while(input!=8);
	scanner.close();
}
//========================================= End =========================================================================	
}
