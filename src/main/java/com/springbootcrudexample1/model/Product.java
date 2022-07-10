package com.springbootcrudexample1.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h2>This class represents the product entity.</h2>
 * This Product class represents the product entity with id, product number, product name and price.
 * 
 * @author seanea
 * @version 1.0
 * @since 2022-06-28
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "productss")
public class Product {
	@Id
	private String id;
	private String productNum;
	private String name;
	private double price;

	/**
	 * This method sets the id to the product.
	 * 
	 * @param id2 The product id.
	 */
	public void setId(String id2) {
		id = id2;
	}

	/**
	 * This method gets the id of the product.
	 * 
	 * @return The id of the product.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * This method sets the product number given to the product.
	 * 
	 * @param prodNum The product number.
	 */
	public void setProductNum(String prodNum) {
		productNum = prodNum;
	}

	/**
	 * This method gets the number of the product.
	 * 
	 * @return The product number.
	 */
	public String getProductNum() {
		return productNum;
	}

	/**
	 * This method sets the name of the product.
	 * 
	 * @param name2 The name of the product.
	 */
	public void setName(String name2) {
		name = name2;
	}
	
	/**
	 * This method gets the name of the product.
	 * 
	 * @return The product name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method sets the price of the product.
	 * 
	 * @param price2 The product price.
	 */
	public void setPrice(double price2) {
		price = price2;
	}

	/**
	 * This method gets the price of the product.
	 * 
	 * @return The product price.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * This method returns the class fields in JSON string format.
	 */
	public String toString() {
		return "{" + "\"id\": " + id + ", \"productNum\": " + productNum + ", \"name\": " + name + ", \"price\": " + price + "}";
	}
}
