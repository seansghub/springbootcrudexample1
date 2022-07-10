package com.springbootcrudexample1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springbootcrudexample1.model.ProductDto;
import com.springbootcrudexample1.repository.IProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This class, ProductServiceTest, is the unit-test for the ProductService class. 
 * It tests the database access methods:
 * getAllProducts, getProductById, getProductInRange, saveProduct, updateProduct, deleteProduct.
 * 
 * @author seanea
 *
 */
@ExtendWith(MockitoExtension.class)
public class ProductServiceUnitTest {
	@Mock
	private IProductRepository productRepository;

	@InjectMocks
	ProductService productService;
	
	private ProductDto productDto;
	
	ProductServiceUnitTest() {
		productDto = new ProductDto();
		productDto.setId("id00");
		productDto.setProductNum("productNum00");
		productDto.setName("name00");
		productDto.setPrice(10.0);
	}
	
	/**
	 * This unit-test case, test_get_all_products, tests
	 * the getAllProducts() method.
	 * 
	 * @throws Exception The Exception when the test_get_all_products() test fails.
	 */
	@Test
	void test_get_all_products() throws Exception {
		// When the method productService.getAllProducts(), of the mocked
		// object productService, is invoked, it does nothing and simply returns null.
		Flux<ProductDto> products = productService.getAllProducts();
		assertEquals(products, null);
	}
	
	/**
	 * This unit-test case, test_get_a_product_of_a_given_id, tests
	 * the getProductById() method.
	 * 
	 * @throws Exception The Exception when the test_get_a_product_of_a_given_id() test fails.
	 */
	@Test
	void test_get_a_product_of_a_given_id() throws Exception {
		// When the method productService.getProductById(), of the mocked
		// object productService, is invoked, it does nothing and simply returns null.
		Mono<ProductDto> products = productService.getProductById("id00");
		assertEquals(products, null);
	}
	/**
	 * This unit-test case, test_get_products_in_range, tests
	 * the getProductInRange() method.
	 * 
	 * @throws Exception The Exception when the test_get_products_in_range() test fails.
	 */
	@Test
	void test_get_products_in_range() throws Exception {
		// When the method productService.getProductInRange(), of the mocked
		// object productService, is invoked, it does nothing and simply returns null.
		Flux<ProductDto> products = productService.getProductInRange(10.0, 20.0);
		assertEquals(products, null);
	}

	/**
	 * This unit-test case, test_save_a_product_dto, tests
	 * the saveProduct() method.
	 * 
	 * @throws Exception The Exception when the test_save_a_product_dto() test fails.
	 */
	@Test
	void test_save_a_product_dto() throws Exception {
		String jsonStringProductDto = "{\"id\":\"id00\",\"productNum\":\"productNum00\",\"name\":\"name00\",\"price\":10.0}";
		// When the method productService.saveProduct(), of the mocked
		// object productService, is invoked, it does nothing and simply returns null.
		Mono<ProductDto> savedUser = productService.saveProduct(jsonStringProductDto);
		assertNotNull(savedUser);
	}
	
	/**
	 * This unit-test case, test_update_a_product_of_a_given_id, tests
	 * the updateProduct() method.
	 * 
	 * @throws Exception The Exception when the test_update_a_product_of_a_given_id() test fails.
	 */
	@Test
	void test_update_a_product_of_a_given_id() throws Exception {
		String jsonStringProductDto = "{\"id\":\"id00\",\"productNum\":\"productNum00\",\"name\":\"name00\",\"price\":10.0}";
		
		// When the method productService.updateProduct(), of the mocked
		// object productService, is invoked, it does nothing and simply returns null.
		Mono<ProductDto> products = productService.updateProduct(jsonStringProductDto);
		assertEquals(products, null);
	}
	
	/**
	 * This unit-test case, test_delete_a_product_of_a_given_id, tests
	 * the deleteProduct() method.
	 * 
	 * @throws Exception The Exception when the test_delete_a_product_of_a_given_id() test fails.
	 */
	@Test
	void test_delete_a_product_of_a_given_id() throws Exception {
		// When the method productService.deleteProduct(), of the mocked
		// object productService, is invoked, it does nothing and simply returns null.
		Mono<Void> products = productService.deleteProduct("id00");
		assertEquals(products, null);
	}
}
