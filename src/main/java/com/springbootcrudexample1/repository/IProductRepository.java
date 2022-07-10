package com.springbootcrudexample1.repository;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.springbootcrudexample1.model.Product;
import com.springbootcrudexample1.model.ProductDto;

import reactor.core.publisher.Flux;

/**
 * <h2>This is an interface for data repository, MongoDB.</h2>
 * This interface declares the database access methods such as CRUD(Create, Read, Update, Delete).
 * 
 * @author seanea
 * @version 1.0
 * @since 2022-06-29
 *
 */
@Repository
public interface IProductRepository extends ReactiveMongoRepository<Product, String> {
	/**
	 * This method finds the price between a price range, a read action.
	 * 
	 * @param priceRange A range of price.
	 * @return The products whose prices are within the price range.
	 */
	Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);
}
