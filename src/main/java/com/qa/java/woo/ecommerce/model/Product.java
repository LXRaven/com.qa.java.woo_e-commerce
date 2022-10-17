package com.qa.java.woo.ecommerce.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public  class Product {
	int id;
	String name;
	double price;
	String category;
	boolean inStock;
	float discountPercentage;
	String deliveryMode;
	boolean isReturnAccepted;
	String sellerName;
}
