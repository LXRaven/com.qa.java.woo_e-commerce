package com.qa.java.woo.ecommerce;

import com.qa.java.woo.ecommerce.controller.ProductController;
import com.qa.java.woo.ecommerce.exception.ProductNotFoundException;
import com.qa.java.woo.ecommerce.model.Product;

public class App {
	
  public static void main(String[] args) throws ProductNotFoundException {
	  	ProductController pc = new ProductController();
	  	
	  	//find by id
	  	System.out.println(pc.getProductById(2));
	  	System.out.println();
	  	
	  	//all products
	  	System.out.println("All Products");
	  	for (Product p : pc.getAllProducts()) {
	  		System.out.println(p);
	  	}
	  	
	  	//add product
	  	Product p1 = new Product(9, "paper", (double) 2.0, "docs", true, (float) 2.5, "paid", true, "Asda");
	  	pc.addProduct(p1);

	  	//update product
	  	Product pupdate = new Product (4, "Sprite", (double)5.2, "drinks", false, (float) 7.8, "free", true, "TESCO");
	  	pc.updateProduct(pupdate);
	  	System.out.println("New Database");
	  	for (Product p : pc.getAllProducts()) {
	  		System.out.println(p);
	  	}
	  	
  }
}
