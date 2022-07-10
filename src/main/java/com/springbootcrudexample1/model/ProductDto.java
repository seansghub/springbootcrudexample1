package com.springbootcrudexample1.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h2>This class represents the product data transfer object.</h2>
 * The ProductDto class represents the Product Data Transfer Object with id, product number, product name, and price.
 * 
 * @author seanea
 * @version 1.0
 * @since 2022-06-28
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String productNum;
	private String name;
	private double price;
	
	/**
	 * This method sets the of the id to the product data transfer object.
	 * 
	 * @param id2 The product data transfer object id.
	 */
	public void setId(String id2) {
		id = id2;
	}

	/**
	 * This method gets the id of the product data transfer object.
	 * 
	 * @return The product data transfer object id.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * This method sets the number to the product data transfer object.
	 * 
	 * @param prodNum The product data transfer object number.
	 */
	public void setProductNum(String prodNum) {
		productNum = prodNum;
	}

	/**
	 * This method gets the number of the product data transfer object.
	 * 
	 * @return The product data transfer object number.
	 */
	public String getProductNum() {
		return productNum;
	}

	/**
	 * This method sets the name to the product data transfer object.
	 * 
	 * @param name2 The product data transfer object name.
	 */
	public void setName(String name2) {
		name = name2;
	}

	/**
	 * This method gets the name of the product data transfer object.
	 * 
	 * @return The product data transfer object name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method sets the price to the product data transfer object.
	 * 
	 * @param price2 The product data transfer object price.
	 */
	public void setPrice(double price2) {
		price = price2;
	}

	/**
	 * This method gets the price of the product data transfer object.
	 * 
	 * @return The product data transfer object price.
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
