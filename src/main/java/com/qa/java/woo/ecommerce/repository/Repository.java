package com.qa.java.woo.ecommerce.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Repository {
	Connection connection = null;
	
	public Connection getDbConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb","root", "root");
			if (connection!=null) {
				System.out.println("Connection successful: empdb");
			}
		} catch (SQLException e) {
		System.out.println("Invalid credentials");
			e.printStackTrace();
		}
	} catch (ClassNotFoundException e) {
	System.out.println("MySQL connector jar file not found in class path");
		e.printStackTrace();
	}
		return connection;
	}
}
