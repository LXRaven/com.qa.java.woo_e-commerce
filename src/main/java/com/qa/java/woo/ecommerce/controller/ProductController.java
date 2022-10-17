package com.qa.java.woo.ecommerce.controller;

import java.util.List;

import com.qa.java.woo.ecommerce.exception.ProductAlreadyExistsException;
import com.qa.java.woo.ecommerce.exception.ProductNotFoundException;
import com.qa.java.woo.ecommerce.model.Product;
import com.qa.java.woo.ecommerce.service.ProductServiceImp;

public class ProductController {
	ProductServiceImp ps = new ProductServiceImp();
	
	public Product getProductById(int id) throws ProductNotFoundException {
		Product p = null;
		try {
			p = ps.getProductById(id);
		} catch (ProductNotFoundException e) {
			System.out.println("Product not found");
		}
		return p;
	}
	public List<Product> getAllProducts() {
		List <Product> plst = ps.getAllProducts();
		return plst;
	}
	
	public Product addProduct(Product product){
		try {
			ps.addProduct(product);
			System.out.println();
			System.out.println("New databse:");
			for (Product p : ps.getAllProducts()) {
		  		System.out.println(p);
		  	}
		} catch (ProductAlreadyExistsException e) {
			System.out.println();
			System.out.println("Specified Product already exists");
		}
		return product;
	}
	public Product updateProduct(Product product) {
		try {
			ps.updateProduct(product);
		} catch (ProductNotFoundException e) {
			System.out.println("Product not found");
		}
		return product;
	}

}
