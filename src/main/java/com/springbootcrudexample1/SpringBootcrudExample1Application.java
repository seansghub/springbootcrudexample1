package com.springbootcrudexample1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <h2>This class implements a micro-service application for database services.</h2>
 *  This class provides the capabilities for database access, CRUD(Create, Read, Update, Delete).
 *  
 *  The database operations such as save, retrieve/read, update, and delete the products.
 *  
 * @author seanea
 * @version 1.0
 * @since 2022-06-29
 *
 */
@SpringBootApplication
public class SpringBootcrudExample1Application {
	// main is the entry point of the application.
	public static void main(String[] args) {
		SpringApplication.run(SpringBootcrudExample1Application.class, args);
	}
}
