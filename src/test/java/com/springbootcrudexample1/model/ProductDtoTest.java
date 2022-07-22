package com.springbootcrudexample1.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * This class, ProductDtoTest, is the unit-test for the ProductDto class. 
 * It tests the getters and setters methods.
 * 
 * @author seanea
 *
 */
public class ProductDtoTest {
	private ProductDto productDto = new ProductDto();
	
	/**
	 * This unit-test case, test_productDto_setId_getId, tests
	 * the setId() and getId() methods.
	 * 
	 * @throws Exception he Exception when the test_productDto_setId_getId() test fails.
	 */
	@Test
	void test_productDto_setId_getId() throws Exception {
		productDto.setId("id00");
		assertEquals("id00", productDto.getId());
	}
	
	/**
	 * This unit-test case, test_productDto_setProductNum_getProductNum, tests
	 * the setProductNum() and getProductNum() methods.
	 * 
	 * @throws Exception he Exception when the test_productDto_setProductNum_getProductNum() test fails.
	 */
	@Test
	void test_productDto_setProductNum_getProductNum() throws Exception {
		productDto.setProductNum("productNum00");
		assertEquals("productNum00", productDto.getProductNum());
	}
	
	/**
	 * This unit-test case, test_productDto_setName_getName, tests
	 * the setName() and getName() methods.
	 * 
	 * @throws Exception he Exception when the test_productDto_setName_getName() test fails.
	 */
	@Test
	void test_productDto_setName_getName() throws Exception {
		productDto.setName("name00");
		assertEquals("name00", productDto.getName());
	}
	
	/**
	 * This unit-test case, test_productDto_setPrice_getPrice, tests
	 * the setPrice() and getPrice() methods.
	 * 
	 * @throws Exception he Exception when the test_productDto_setName_getName() test fails.
	 */
	@Test
	void test_productDto_setPrice_getPrice() throws Exception {
		productDto.setPrice(10.0);
		assertEquals(10.0, productDto.getPrice());
	}
}
