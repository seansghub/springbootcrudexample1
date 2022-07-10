package com.springbootcrudexample1.utils;

import com.springbootcrudexample1.model.Product;
import com.springbootcrudexample1.model.ProductDto;

/** 
 * <h2>Class to convert entity class to dto and vice versa.</h2>
 * This utility class implements the conversion from entity class to DTO class and Vice versa.
 * 
 * @author seanea
 * @version 1.0
 * @since 2022-06-28
 *
 */
public class AppUtils {
	/**
	 * This method converts Product entity to ProductDto(Product Data Transfer Object).
	 * 
	 * @param product The Product entity object.
	 * @return ProductDto - The Product Data Transfer Object.
	 */
	public static ProductDto entityToDto(Product product) {
		ProductDto productDto = new ProductDto();

		productDto.setId(product.getId());
		productDto.setProductNum(product.getProductNum());
		productDto.setName(product.getName());
		productDto.setPrice(product.getPrice());
//        Note: 
//		  BeanUtils.copyProperties() does not really work.
//        Eg: BeanUtils.copyProperties(product, productDto);

		return productDto;
	}

	/**
	 * This method converts ProductDto(Product Data Transfer Object) to Product entity.
	 * 
	 * @param productDto The Product Data Transfer Object.
	 * @return Product - The Product object.
	 */
	public static Product dtoToEntity(ProductDto productDto) {
		Product product = new Product();

		product.setId(productDto.getId());
		product.setProductNum(productDto.getProductNum());
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());

		return product;
	}
}
