package com.qa.java.woo.ecommerce.service;

import java.util.List;

import com.qa.java.woo.ecommerce.exception.ProductAlreadyExistsException;
import com.qa.java.woo.ecommerce.exception.ProductNotFoundException;
import com.qa.java.woo.ecommerce.model.Product;

public interface ProductService {
	Product getProductById(int id) throws ProductNotFoundException;
	List<Product> getAllProducts();
	Product addProduct(Product product) throws ProductAlreadyExistsException;
	Product updateProduct(Product product) throws ProductNotFoundException;
	boolean deleteProduct(int id) throws ProductNotFoundException;

}
