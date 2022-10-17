package com.qa.java.woo.ecommerce.service;

import java.sql.Connection;
import java.util.List;

import com.qa.java.woo.ecommerce.exception.ProductAlreadyExistsException;
import com.qa.java.woo.ecommerce.exception.ProductNotFoundException;
import com.qa.java.woo.ecommerce.model.Product;
import com.qa.java.woo.ecommerce.repository.Repository;

public class ProductServiceImp implements ProductService {
	Repository repo = new Repository();
	Connection connection = repo.getDbConnection();

	@Override
	public Product getProductById(int id) throws ProductNotFoundException {
		Product p = repo.getProductById(id);
		if (p == null)
			throw new ProductNotFoundException("Product does not exist");
		return p;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> plst = repo.getAllProducts();
		return plst;
	}

	@Override
	public Product addProduct(Product product) throws ProductAlreadyExistsException {
		for (Product p : repo.getAllProducts()) {
			if (p.getName().equals(product.getName()) || p.getId()==product.getId()) {
				throw new ProductAlreadyExistsException("Product already in database");
			} else {
				repo.addProduct(product);
			  	}
			}
		return product;
	}

	@Override
	public Product updateProduct(Product product) throws ProductNotFoundException {
		if (repo.getProductById(product.getId())==null) throw new ProductNotFoundException("Not found");
		else {
			System.out.println(String.format("updated item with the id %d", product.getId()));
			repo.updateProduct(product);
			return product;
		}
	}

	@Override
	public boolean deleteProduct(int id) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

}
