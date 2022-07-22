package com.springbootcrudexample1.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * This class, ProductTest, is the unit-test for the Product class. 
 * It tests the getters and setters methods.
 * 
 * @author seanea
 *
 */
public class ProductTest {	
	private Product product = new Product();
	
	/**
	 * This unit-test case, test_product_setId_getId, tests
	 * the setId() and getId() methods.
	 * 
	 * @throws Exception he Exception when the test_product_setId_getId() test fails.
	 */
	@Test
	void test_product_setId_getId() throws Exception {
		product.setId("id00");
		assertEquals("id00", product.getId());
	}
	
	/**
	 * This unit-test case, test_product_setProductNum_getProductNum, tests
	 * the setProductNum() and getProductNum() methods.
	 * 
	 * @throws Exception he Exception when the test_product_setProductNum_getProductNum() test fails.
	 */
	@Test
	void test_product_setProductNum_getProductNum() throws Exception {
		product.setProductNum("productNum00");
		assertEquals("productNum00", product.getProductNum());
	}
	
	/**
	 * This unit-test case, test_product_setName_getName, tests
	 * the setName() and getName() methods.
	 * 
	 * @throws Exception he Exception when the test_product_setName_getName() test fails.
	 */
	@Test
	void test_product_setName_getName() throws Exception {
		product.setName("name00");
		assertEquals("name00", product.getName());
	}
	
	/**
	 * This unit-test case, test_product_setPrice_getPrice, tests
	 * the setPrice() and getPrice() methods.
	 * 
	 * @throws Exception he Exception when the test_product_setPrice_getPrice() test fails.
	 */
	@Test
	void test_product_setPrice_getPrice() throws Exception {
		product.setPrice(10.0);
		assertEquals(10.0, product.getPrice());
	}
}
