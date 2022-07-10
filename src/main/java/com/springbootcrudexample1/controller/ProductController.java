package com.springbootcrudexample1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootcrudexample1.model.ProductDto;
import com.springbootcrudexample1.service.ProductService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <h2>This class provides the HTTP end-points for database access.</h2> This
 * controller class provides HTTP end-points for the database methods,
 * CRUD(Create, Read, Update, Delete). That is database actions such as
 * retrieve, save, update, and delete product(s).
 * 
 * @author seanea
 * @version 1.0
 * @since 2022-06-29
 *
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
	/**
	 * 
	 * Dependency injection for ProductService object.
	 */
	@Autowired
	private ProductService productService;

	/**
	 * This end-point, retrieves all of the products in the database.
	 * 
	 * @return All the products.
	 */
	@GetMapping
	public Flux<ProductDto> getAllProducts() {
		return productService.getAllProducts();
	}

	/**
	 * This end-point, retrieves a product from the database with a specified
	 * id.
	 * 
	 * @param id The product id.
	 * @return The product with the specified id.
	 */
	@GetMapping("/{id}")
	public Mono<ProductDto> getProductById(@PathVariable String id) {
		return productService.getProductById(id);
	}

	/**
	 * This end-point, retrieves products whose prices are within the given
	 * range.
	 * 
	 * @param min The minimum value of the range.
	 * @param max The maximum value or the range.
	 * @return The products whose prices are within the range.
	 */
	@GetMapping("/product_range")
	public Flux<ProductDto> getProductInRange(@RequestParam("min") double min, @RequestParam("max") double max) {
		return productService.getProductInRange(min, max);
	}

	/**
	 * This end-point, saves a given product to the database.
	 * 
	 * @param productJsonString A JSON string representation of the product.
	 * @return The product which has been saved in the database.
	 */
	@PostMapping
	public Mono<ProductDto> saveProduct(@RequestBody String productJsonString) {
		if (productJsonString.length() == 0) {
			return null;
		}
		return productService.saveProduct(productJsonString);
	}

	/**
	 * This end-point, updates/overwrites the existing product in the database, 
	 * with the given product.
	 * 
	 * @param productJsonString The product in the form of JSON string.
	 * @return The updated/overwritten product.
	 */
	@PostMapping("/update")
	public Mono<ProductDto> updateProduct(@RequestBody String productJsonString) {
		if (productJsonString.length() == 0) {
			return null;
		}
		return productService.updateProduct(productJsonString);
	}

	/**
	 * This end-point, removes the given product from the database.
	 * 
	 * @param productJsonString The product in the form of JSON string.
	 * @return Nothing.
	 */
	@PostMapping("/delete")
	public Mono<Void> deleteProduct(@RequestBody String productJsonString) {
		if (productJsonString.length() == 0) {
			return null;
		}
		return productService.deleteProduct(productJsonString);
	}
}
