package com.springbootcrudexample1.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;

import com.springbootcrudexample1.model.ProductDto;
import com.springbootcrudexample1.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This class, ProductControllerTest, is the unit-test for the ProductController class. It tests
 * the REST end-points for all database access(i.e. getAllProducts,
 * getProductById, getProductInRange, saveProduct, updateProduct, and
 * deleteProduct) for this application.
 * 
 * @author seanea
 *
 */
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@WebFluxTest(controllers = ProductController.class)
public class ProductControllerUnitTest {
	// Mock the ProductService class by creating an instance of it and inject the
	// instance into the application context.
	// The Mocked instance can then be used to test the methods, the REST end-points
	// in the ProductController class,
	// without having to perform the actual database access. When the REST
	// end-points invoke a method
	// of the Mocked object, in this case an object of ProductService class, it does
	// nothing and return
	// what the end-point is supposed to return.

	@MockBean
	private ProductService productService;

	// Create and instance, bean, of the WebTestClient class and inject it into the
	// application context.
	@Autowired
	private WebTestClient webTestClient;

	/**
	 * This unit test case "test_end_point_get_all_products" tests the
	 * retrieval of all products in the database.
	 * 
	 * @throws Exception The Exception when the test_get_all_products() test fails.
	 */
	@Test
	public void test_end_point_get_all_products() throws Exception {

		ProductDto productDto_00 = new ProductDto();
		productDto_00.setId("id00");
		productDto_00.setProductNum("productNum00");
		productDto_00.setName("name00");
		productDto_00.setPrice(10.0);

		ProductDto productDto_01 = new ProductDto();
		productDto_01.setId("id01");
		productDto_01.setProductNum("productNum01");
		productDto_01.setName("name01");
		productDto_01.setPrice(11.0);

		List<ProductDto> listOfProductDto = new ArrayList<ProductDto>();
		listOfProductDto.add(productDto_00);
		listOfProductDto.add(productDto_01);

		// Convert an ArrayList of ProductDto to a Flux of ProductDto for
		// Mocking the getAllProducts() method of the productService class.
		Flux<ProductDto> productDtoFlux = Flux.fromIterable(listOfProductDto);

		// Mock the end-point for HTTP GET.
		// --------------
		// The code:
		// Mockito.when().thenReturn()
		//
		// is related to the code:
		// webTestClient.get().uri("/products").exchange(). ...
		// --------------
		// When the productService.getAllProducts() is invoked, at the 
		// GET REST end-point, "/products", it does nothing and simply 
		// return what the REST end-point is supposed to return,
		// the object of Flux<ProductDto> which is productDtoFlux.
		Mockito.when(productService.getAllProducts()).thenReturn(productDtoFlux);

		// Test end-point, HTTP GET.
		// --------------
		// The productService.getAllProducts() method is invoked at the GET REST
		// end-point "/products".
		// The flux of ProductDto is returned in the "result" in the form of the
		// ResponseBody.
		webTestClient.get().uri("/products").exchange().expectStatus().isOk().expectBodyList(ProductDto.class)
				.consumeWith(result -> {

					// Extract the list of ProductDto from the ResponseBody.
					List<ProductDto> responseBody = result.getResponseBody();
					// Test the returned list of ProductDto.
					assertEquals(responseBody.get(0).getId(), "id00");
					assertEquals(responseBody.get(0).getProductNum(), "productNum00");
					assertEquals(responseBody.get(0).getName(), "name00");
					assertEquals(responseBody.get(0).getPrice(), 10.0);

					assertEquals(responseBody.get(1).getId(), "id01");
					assertEquals(responseBody.get(1).getProductNum(), "productNum01");
					assertEquals(responseBody.get(1).getName(), "name01");
					assertEquals(responseBody.get(1).getPrice(), 11.0);
				});

		// Verify that the productService.getAllProducts() method is invoked one time,
		// once.
		Mockito.verify(productService, times(1)).getAllProducts();
	}

	/**
	 * This unit test case "test_end_point_get_product_by_id" tests the
	 * retrieval of a product of a given id in the database.
	 * 
	 * @throws Exception The Exception when the test_end_point_get_product_by_id()
	 *                   test fails.
	 */
	@Test
	public void test_end_point_get_product_by_id() throws Exception {

		ProductDto productDto = new ProductDto();
		productDto.setId("id00");
		productDto.setProductNum("productNum00");
		productDto.setName("name00");
		productDto.setPrice(10.0);

		// Mock the end-point for HTTP GET.
		// --------------
		// The code:
		// Mockito.when().thenReturn()
		//
		// is related to the code:
		// webTestClient.get().uri("/products/id00").exchange(). ...
		// --------------
		// When the productService.getProductById("id00") is invoked, at the
		// "/products/{id}" GET REST end-point, it does nothing and simply return
		// what the REST end-point is supposed to return,
		// i.e. the object of Mono<ProductDto> which is Mono.just(productDto).
		Mockito.when(productService.getProductById("id00")).thenReturn(Mono.just(productDto));

		// Test end-point, HTTP GET.
		// --------------
		// The productService.getProductById("id00") method is invoked at the GET REST
		// end-point "/products/{id}".
		// The Mono of ProductDto is returned in the "result" in the form of the
		// ResponseBody.
		webTestClient.get().uri("/products/id00").exchange().expectStatus().isOk().expectBodyList(ProductDto.class)
				.consumeWith(result -> {

					// Extract the list of ProductDto from the ResponseBody. In this case, a list of
					// one element.
					List<ProductDto> responseBody = result.getResponseBody();
					// Test the returned list of ProductDto.
					assertEquals(responseBody.get(0).getId(), "id00");
					assertEquals(responseBody.get(0).getProductNum(), "productNum00");
					assertEquals(responseBody.get(0).getName(), "name00");
					assertEquals(responseBody.get(0).getPrice(), 10.0);
				});

		// Verify that the productService.getProductById("id00") method is invoked one
		// time, once.
		Mockito.verify(productService, times(1)).getProductById("id00");
	}

	/**
	 * This unit test case "test_end_point_get_product_in_price_range" tests
	 * the retrieval of products, from the database, between a price range.
	 * 
	 * @throws Exception The Exception when the
	 *                   test_end_point_get_product_in_price_range() test fails.
	 */
	@Test
	public void test_end_point_get_product_in_price_range() throws Exception {

		ProductDto productDto_00 = new ProductDto();
		productDto_00.setId("id00");
		productDto_00.setProductNum("productNum00");
		productDto_00.setName("name00");
		productDto_00.setPrice(10.0);

		ProductDto productDto_01 = new ProductDto();
		productDto_01.setId("id01");
		productDto_01.setProductNum("productNum01");
		productDto_01.setName("name01");
		productDto_01.setPrice(11.0);

		ProductDto productDto_02 = new ProductDto();
		productDto_02.setId("id02");
		productDto_02.setProductNum("productNum02");
		productDto_02.setName("name02");
		productDto_02.setPrice(12.0);

		List<ProductDto> listOfProductDto = new ArrayList<ProductDto>();
		listOfProductDto.add(productDto_00);
		listOfProductDto.add(productDto_01);
		listOfProductDto.add(productDto_02);

		// Convert an ArrayList of ProductDto to a Flux of ProductDto for
		// Mocking the getProductInRange() method of the ProductService class.
		Flux<ProductDto> productDtoFlux = Flux.fromIterable(listOfProductDto);

		// Mock the end-point for HTTP GET.
		// --------------
		// The code:
		// Mockito.when().thenReturn()
		//
		// is related to the code:
		// webTestClient.get().uri("/products/product_range").exchange(). ...
		// --------------
		// When the productService.getProductInRange(10.0, 15.0) is invoked, at the
		// "/products/product-range" REST GET end-point, it does nothing and simply
		// return what the REST end-point is supposed to return, 
		// i.e. the object of Flux<ProductDto>.
		Mockito.when(productService.getProductInRange(10.0, 15.0)).thenReturn(productDtoFlux);

		// Test end-point, HTTP GET.
		// --------------
		// The productService.getProductInRange(10.0, 15.0) method is invoked at the GET
		// REST end-point "/products/product-range".
		// The Flux of ProductDto is returned in the "result" in the form of the
		// ResponseBody.
		webTestClient.get().uri("/products/product_range?min=10.0&max=15.0").exchange().expectStatus().isOk()
				.expectBodyList(ProductDto.class).consumeWith(result -> {

					// Extract the list of ProductDto from the ResponseBody.
					List<ProductDto> responseBody = result.getResponseBody();
					// Test the returned list of ProductDto.
					assertEquals(responseBody.get(0).getId(), "id00");
					assertEquals(responseBody.get(0).getProductNum(), "productNum00");
					assertEquals(responseBody.get(0).getName(), "name00");
					assertEquals(responseBody.get(0).getPrice(), 10.0);

					assertEquals(responseBody.get(1).getId(), "id01");
					assertEquals(responseBody.get(1).getProductNum(), "productNum01");
					assertEquals(responseBody.get(1).getName(), "name01");
					assertEquals(responseBody.get(1).getPrice(), 11.0);

					assertEquals(responseBody.get(2).getId(), "id02");
					assertEquals(responseBody.get(2).getProductNum(), "productNum02");
					assertEquals(responseBody.get(2).getName(), "name02");
					assertEquals(responseBody.get(2).getPrice(), 12.0);
				});

		// Verify that the productService.getProductInRange(10.0, 15.0) method is
		// invoked one time, once.
		Mockito.verify(productService, times(1)).getProductInRange(10.0, 15.0);
	}

	/**
	 * This unit test case "test_end_point_save_product", tests the saving of a
	 * product by HTTP POST.
	 * 
	 * @throws Exception The Exception when the test_end_point_save_product() test
	 *                   fails.
	 */
	@Test
	public void test_end_point_save_product() throws Exception {
		ProductDto productDto_00 = new ProductDto();
		productDto_00.setId("id00");
		productDto_00.setProductNum("productNum00");
		productDto_00.setName("name00");
		productDto_00.setPrice(10.0);

		String jsonStringProductDto_00 = "{\"id\":\"id00\",\"productNum\":\"productNum00\",\"name\":\"name00\",\"price\":10.0}";
		Mono<ProductDto> productDtoMono = Mono.just(productDto_00);

		// Mock the end-point for HTTP POST.
		// --------------
		// The code:
		// The code:
		// Mockito.when().thenReturn()
		//
		// is related to the code:
		// webTestClient.post().uri("/products").exchange(). ...
		// --------------
		// When the productService.saveProduct(jsonStringProductDto_00) is invoked, at
		// the "/products" HTTP POST end-point, it does nothing and simply return
		// what the HTTP POST end-point is supposed to return, i.e. the object of
		// Mono<ProductDto>.
		Mockito.when(productService.saveProduct(jsonStringProductDto_00)).thenReturn(productDtoMono);

		// Test end-point, HTTP POST.
		// --------------
		// The productService.saveProduct(jsonStringProductDto_00) method is invoked at the
		// HTTP POST end-point "/products".
		// The List<ProductDto> object is returned in the "result" in the form of the
		// ResponseBody.
		webTestClient.post().uri("/products").accept(MediaType.APPLICATION_JSON).body(productDtoMono, ProductDto.class)
				.exchange().expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(ProductDto.class).consumeWith(result -> {

					// Extract the list of ProductDto from the ResponseBody.
					List<ProductDto> responseBody = result.getResponseBody();
					// Test the returned list of one ProductDto.
					assertEquals(responseBody.get(0).getId(), "id00");
					assertEquals(responseBody.get(0).getProductNum(), "productNum00");
					assertEquals(responseBody.get(0).getName(), "name00");
					assertEquals(responseBody.get(0).getPrice(), 10.0);
				});

		// Verify that the productService.saveProduct(jsonStringProductDto_00) method is
		// invoked one time, once.
		Mockito.verify(productService, times(1)).saveProduct(jsonStringProductDto_00);
	}

	/**
	 * This unit test case "test_end_point_update_product", tests the updating of a
	 * product by HTTP POST.
	 * 
	 * @throws Exception The Exception when the test_end_point_update_product() test
	 *                   fails.
	 */
	@Test
	public void test_end_point_update_product() throws Exception {
		ProductDto productDto_00 = new ProductDto();
		productDto_00.setId("id00");
		productDto_00.setProductNum("productNum00");
		productDto_00.setName("name00");
		productDto_00.setPrice(10.0);

		Mono<ProductDto> productDtoMono = Mono.just(productDto_00);
		String jsonStringProductDto_00 = "{\"id\":\"id00\",\"productNum\":\"productNum00\",\"name\":\"name00\",\"price\":10.0}";

		// Mock the end-point for HTTP POST.
		// --------------
		// The code:
		// Mockito.when().thenReturn()
		//
		// is related to the code:
		// webTestClient.post().uri("/products/update").exchange(). ...
		// --------------
		// When the productService.updateProduct(jsonStringProductDto_00) is invoked, at
		// the "/products/update" HTTP POST end-point, it does nothing and simply return
		// what the POST end-point is supposed to return, i.e. the object of
		// Mono<ProductDto>.
		Mockito.when(productService.updateProduct(jsonStringProductDto_00)).thenReturn(productDtoMono);

		// Test end-point, HTTP POST.
		// --------------
		// The productService.updateProduct(String aJsonString) method is invoked at the
		// POST end-point "/products/update". It returns the List<ProductDto> object in 
		// the "result" in the form of the ResponseBody.
		webTestClient.post().uri("/products/update").accept(MediaType.APPLICATION_JSON)
				.body(productDtoMono, ProductDto.class).exchange().expectStatus().isOk().expectHeader()
				.contentType(MediaType.APPLICATION_JSON).expectBodyList(ProductDto.class).consumeWith(result -> {

					// Extract the list of ProductDto from the ResponseBody.
					List<ProductDto> responseBody = result.getResponseBody();
					// Test the returned list of one ProductDto.
					assertEquals(responseBody.get(0).getId(), "id00");
					assertEquals(responseBody.get(0).getProductNum(), "productNum00");
					assertEquals(responseBody.get(0).getName(), "name00");
					assertEquals(responseBody.get(0).getPrice(), 10.0);
				});

		// Verify that the productService.updateProduct(jsonStringProductDto_00) method is
		// invoked one time, once.
		Mockito.verify(productService, times(1)).updateProduct(jsonStringProductDto_00);
	}

	/**
	 * This unit test case "test_end_point_delete_product", tests the deleting of a
	 * product by HTTP POST.
	 * 
	 * @throws Exception The Exception when the test_end_point_delete_product() test
	 *                   fails.
	 */
	@Test
	public void test_end_point_delete_product() throws Exception {
		ProductDto productDto_00 = new ProductDto();
		productDto_00.setId("id00");
		productDto_00.setProductNum("productNum00");
		productDto_00.setName("name00");
		productDto_00.setPrice(10.0);

		Mono<ProductDto> productDtoMono = Mono.just(productDto_00);
		String jsonStringProductDto_00 = "{\"id\":\"id00\",\"productNum\":\"productNum00\",\"name\":\"name00\",\"price\":10.0}";

		// Mock the end-point for HTTP POST.
		// --------------
		// The code:
		// Mockito.when().thenReturn()
		//
		// is related to the code:
		// webTestClient.post().uri("/products/delete").exchange(). ...
		// --------------
		// When the productService.deleteProduct(jsonStringProductDto_00) is invoked, at
		// the "/products/delete" HTTP POST end-point, it does nothing and simply return
		// what the POST end-point is supposed to return, i.e. the object of
		// Mono<Void> the same as Mono.empty().
		Mockito.when(productService.deleteProduct(jsonStringProductDto_00)).thenReturn(Mono.empty());

		// Test end-point, HTTP POST.
		// --------------
		// The productService.deleteProduct(jsonStringProductDto_00) method is invoked at the
		// POST end-point "/products/delete".
		// Mono<Void> the same as Mono.empty() is returned in the "expectBodyList" which is empty, ie hasSize(0).

		webTestClient.post().uri("/products/delete").accept(MediaType.APPLICATION_JSON)
				.body(productDtoMono, ProductDto.class).exchange().expectStatus().isOk()
				.expectBodyList(ProductDto.class).hasSize(0);

		// Verify that the deleteProduct(jsonStringProductDto_00) method is
		// invoked one time, once.
		Mockito.verify(productService, times(1)).deleteProduct(jsonStringProductDto_00);
	}
}
