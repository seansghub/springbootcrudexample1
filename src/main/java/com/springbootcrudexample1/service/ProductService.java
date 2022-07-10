package com.springbootcrudexample1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootcrudexample1.model.ProductDto;
import com.springbootcrudexample1.repository.IProductRepository;
import com.springbootcrudexample1.utils.AppUtils;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <h2>This class provides the services for database access, CRUD(Create, Read,
 * Update, Delete).</h2> The save method creates the product entity and saves it
 * to the database. The update method creates the product entity and overwrite
 * the corresponding existing one in the database. The find method search for a
 * product or products between a price range. The delete method search for an
 * existing product in the database and deletes it.
 * 
 * @author seanea
 * @version 1.0
 * @since 2022-06-29
 *
 */
@Service
@RequiredArgsConstructor
public class ProductService {
	@Autowired
	private IProductRepository repository;

	/**
	 * This method find all the products in the database.
	 * 
	 * @return All of the products in the database.
	 */
	public Flux<ProductDto> getAllProducts() {
		if (repository.findAll() == null) {
			return null;
		}
		return repository.findAll().map(AppUtils::entityToDto);
	}

	/**
	 * This method finds a product with the specified id.
	 * 
	 * @param id The specified id.
	 * @return The product with the specified id.
	 */
	public Mono<ProductDto> getProductById(String id) {
		if (repository.findById(id) == null) {
			return null;
		}
		return repository.findById(id).map(AppUtils::entityToDto);
	}

	/**
	 * This method finds the products between a price range.
	 * 
	 * @param min The minimum price.
	 * @param max The maximum price.
	 * @return The products between a price range.
	 */
	public Flux<ProductDto> getProductInRange(double min, double max) {
		return repository.findByPriceBetween(Range.closed(min, max));
	}

	/**
	 * This method, from product data transfer object, creates the product entity
	 * and saves it to the database.
	 * 
	 * @param productDtoMono A product data transfer object.
	 * @return A product data transfer object.
	 */
	public Mono<ProductDto> saveProduct(String productJsonString) {
		try {
			// Map the JSON string representation of the product to the product data
			// transfer object.
			ProductDto productDto = new ObjectMapper().readValue(productJsonString, ProductDto.class);
			// Justify the product data transfer object as a single flux object.
			Mono<ProductDto> productDtoMono = Mono.just(productDto);

			return productDtoMono.map(AppUtils::dtoToEntity).flatMap(repository::insert).map(AppUtils::entityToDto);
		} catch (Exception ex) {
			System.err.println("\nError: \n" + ex + "\n");
			return null;
		}
	}

	/**
	 * This method, from a product data transfer object, creates a product entity
	 * and saves it to the database by overwrite it on the specified product id.
	 * 
	 * @param productDto The product data transfer object.
	 * @param id         The id of the product in the database to be overwritten.
	 * @return The product data transfer object.
	 */
	public Mono<ProductDto> updateProduct(String productJsonString) {
		try {
			// Map the JSON string representation of the product to the product data
			// transfer object.
			ProductDto productDto = new ObjectMapper().readValue(productJsonString, ProductDto.class);
			String id = productDto.getId();
			// Justify the product data transfer object as a single flux object.
			Mono<ProductDto> productDtoMono = Mono.just(productDto);

			if (repository.findById(id) == null) {
				return null;
			}
			return repository.findById(id)
					.flatMap(p -> productDtoMono.map(AppUtils::dtoToEntity).doOnNext(e -> e.setId(id)))
					.flatMap(repository::save).map(AppUtils::entityToDto);
		} catch (Exception ex) {
			System.err.println("\nError: \n" + ex + "\n");
			return null;
		}
	}

	/**
	 * This method removes a product with a specified id from the database.
	 * 
	 * @param id The id of the product.
	 * @return Nothing.
	 */
	public Mono<Void> deleteProduct(String productJsonString) {
		
		try {
			// Map the JSON string representation of the product to the product data
			// transfer object.
			ProductDto productDto = new ObjectMapper().readValue(productJsonString, ProductDto.class);
			String id = productDto.getId();

			if (repository.findById(id) == null) {
				return null;
			}
			return repository.deleteById(id);
		} catch (Exception ex) {
			System.err.println("\nError: \n" + ex + "\n");
			return null;
		}
	}
}
