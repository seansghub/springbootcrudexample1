package com.springbootcrudexample1.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.springbootcrudexample1.model.Product;
import com.springbootcrudexample1.model.ProductDto;

/**
 * This class, AppUtilsTest, is the unit-test for the AppUtils class. 
 * It tests the conversion of entity to dto and vice versa, methods:
 * entityToDto, dtoToEntity.
 * 
 * @author seanea
 *
 */
public class AppUtilsUnitTest {
	
	/**
	 * This unit-test case, test_conversion_of_entity_to_dto, tests
	 * the entityToDto() method.
	 * 
	 * @throws Exception The Exception when the test_conversion_of_entity_to_dto() test fails.
	 */
	@Test
	void test_conversion_of_entity_to_dto() throws Exception {
		Product product = new Product();
		ProductDto productDto;
		
		product.setId("id00");
		product.setProductNum("productNum00");
		product.setName("name00");
		product.setPrice(10.0);
		
		productDto = AppUtils.entityToDto(product);
		
		assertEquals(productDto.getId(), product.getId());
		assertEquals(productDto.getProductNum(), product.getProductNum());
		assertEquals(productDto.getName(), product.getName());
		assertEquals(productDto.getPrice(), product.getPrice());
		assertEquals(productDto.toString(), product.toString());
	}
	
	/**
	 * This unit-test case, test_conversion_of_dto_to_entity, tests
	 * the dtoToEntity() method.
	 * 
	 * @throws Exception The Exception when the test_conversion_of_dto_to_entity() test fails.
	 */
	@Test
	void test_conversion_of_dto_to_entity() throws Exception {
		Product product;
		ProductDto productDto = new ProductDto();
		
		productDto.setId("id00");
		productDto.setProductNum("productNum00");
		productDto.setName("name00");
		productDto.setPrice(10.0);
		
		product = AppUtils.dtoToEntity(productDto);
		
		assertEquals(productDto.getId(), product.getId());
		assertEquals(productDto.getProductNum(), product.getProductNum());
		assertEquals(productDto.getName(), product.getName());
		assertEquals(productDto.getPrice(), product.getPrice());
		assertEquals(productDto.toString(), product.toString());
	}
}
