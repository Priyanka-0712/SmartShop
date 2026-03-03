package com.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

//=============== Declaring and Initializing Variables ===================================
	private static final String DB_DRIVER_CLASS="com.mysql.cj.jdbc.Driver";
	private static final String DB_URL="jdbc:mysql://localhost:3306/myshop";
	private static final String DB_USER_NAME="root";
	private static final String DB_USER_PASSWORD="root";

//=================== Getting Connection Object ===========================================

	public static Connection getConnection() throws SQLException
	{
		Connection connection=null;
		try {
			Class.forName(DB_DRIVER_CLASS);
			connection=DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}
//=========================== End ========================================================
}
