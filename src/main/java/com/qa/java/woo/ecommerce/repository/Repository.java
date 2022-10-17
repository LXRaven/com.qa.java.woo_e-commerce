package com.qa.java.woo.ecommerce.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qa.java.woo.ecommerce.exception.ProductAlreadyExistsException;
import com.qa.java.woo.ecommerce.exception.ProductNotFoundException;
import com.qa.java.woo.ecommerce.model.Product;

public class Repository {
	Connection connection = null;

	public Connection getDbConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/we_e-commerce", "root",
						"root");
				if (connection != null) {
					System.out.println("Connection successful: wo_e-commerce");
				}
			} catch (SQLException e) {
				System.out.println("Invalid credentials");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL connector jar file not found in class path");
		}
		return connection;
	}

	public Product getProductById(int inputId) {
		Product p = new Product();
		String query = "SELECT * FROM `we_e-commerce`.products WHERE ID=" + inputId;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			p.setId(inputId);
			p.setName(rs.getString("Name"));
			p.setPrice(rs.getDouble("Price"));
			p.setCategory(rs.getString("Category"));
			p.setDeliveryMode(rs.getString("DeliveryMode"));
			p.setDiscountPercentage(rs.getFloat("DiscountPercentage"));
			p.setInStock((rs.getInt("InStock") == 1));
			p.setReturnAccepted((rs.getInt("ReturnAccepted") == 1));
			p.setSellerName(rs.getString("SellerName"));

		} catch (SQLException e) {
		}

		return p;
	}

	public List<Product> getAllProducts() {
		List<Product> plst = new ArrayList<Product>();
		String query = "SELECT * FROM `we_e-commerce`.products";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Product p = new Product(rs.getInt("ID"), rs.getString("Name"), rs.getDouble("Price"),
						rs.getString("Category"), (rs.getInt("InStock") == 1), rs.getFloat("DiscountPercentage"),
						rs.getString("DeliveryMode"), (rs.getInt("ReturnAccepted") == 1),
						rs.getString("SellerName"));
				plst.add(p);
			}
		} catch (SQLException e) {
		}

		return plst;
	}

	public Product addProduct(Product p) {
		int inStock = p.isInStock() ? 1 : 0;
		int returnAvailable = p.isReturnAccepted() ? 1 : 0;
		String query = "INSERT INTO products VALUES(%d, '%s', %f, '%s', %d, %f, '%s', %d, '%s')";
		query = String.format(query, p.getId(), p.getName(), p.getPrice(), p.getCategory(), inStock,
				p.getDiscountPercentage(), p.getDeliveryMode(), returnAvailable, p.getSellerName());
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			System.out.println();
			System.out.println(String.format("Added: %s to the database.", p.getName()));
		} catch (SQLException e) {
		}

		return p;

	}

	public Product updateProduct(Product product) {
		Product updatedProduct = null;
		int inStock = (product.isInStock() ? 1 : 0);
		int returnAccepted = (product.isReturnAccepted() ? 1 : 0);
//		try {
//			String insertProductQuery = "update products set name = ? , price = ?,"
//					+ "category = ?, `in stock` = ?, `discount percentage` = ?, `delivery mode` = ? ,"
//					+ "`return accepted`=?, `seller name` = ? where id = ?";
//			PreparedStatement pstmt = connection.prepareStatement(insertProductQuery);
//			pstmt.setInt(10, product.getId());
//			pstmt.setString(1, product.getName());
//			pstmt.setDouble(2, product.getPrice());
//			pstmt.setString(4, product.getCategory());
//			pstmt.setInt(5, inStock);
//			pstmt.setFloat(6, product.getDiscountPercentage());
//			pstmt.setString(7, product.getDeliveryMode());
//			pstmt.setInt(8, returnAvailable);
//			pstmt.setString(9, product.getSellerName());
//
//			int rows = pstmt.executeUpdate();
//			if (rows > 0)
//				updatedProduct = product;
//
//		} catch (SQLException e) {
//			System.out.println("Some internal error occured .. Please try again !!");

//		discount_percentage= %f, delivery_mode = '%s', return_available = %d, seller_name= '%s'
//		product.getDiscountPercentage(), product.getDeliveryMode(), returnAvailable,
//		product.getSellerName(), product.getId())
//		}
		// String query = String.format("Update products Set name =`%s` where id= %d",
		//Delivery_Mode = '%s', Return_Available = %d, Seller_Name = '%s'
		//product.getDeliveryMode(), returnAvailable,product.getSellerName(),
		// product.getName(), product.getId());
		Statement stmt;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(String.format("Update products Set Name = '%s', price = %f , category = '%s', InStock = %d, DiscountPercentage = %f, DeliveryMode = '%s', ReturnAccepted = %d, SellerName = '%s' where id=%d",  product.getName(),product.getPrice(), product.getCategory(), inStock, product.getDiscountPercentage(), product.getDeliveryMode(), returnAccepted, product.getSellerName(), product.getId()));
		} catch (SQLException e) {
			System.out.println("error");
		}
		return updatedProduct;
	}

	public boolean deleteProduct(int id) {
		return false;
	}

}
