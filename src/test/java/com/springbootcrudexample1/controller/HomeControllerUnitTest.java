package com.springbootcrudexample1.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * This class, HomeControllerTest, is the unit-test for the HomeController class. It tests
 * the REST GET end-point for fetching the Home Page for this application.
 * 
 * @author seanea
 *
 */
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@WebFluxTest(controllers = HomeController.class)
public class HomeControllerUnitTest {
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
	private HomeController homeController;
	// Create and instance, bean, of the WebTestClient class and inject it into the
	// application context.
	@Autowired
	private WebTestClient webTestClient;

	/**
	 * This unit test case "test_get_home_page" tests the
	 * retrieval of the Home Page for this application.
	 * 
	 * @throws Exception The Exception when the test_get_home_page() test fails.
	 */
	@Test
	void test_get_home_page() throws Exception {
		String homePage = "Hello, this is the product home page";

		// Mock the end-point for HTTP GET.
		// --------------
		// The code: 
		// Mockito.when().thenReturn()
		//
		// is related to and preparing for:
		// webTestClient.get().uri("/").exchange(). ...
		// --------------
		// When the productService.getHomePage() is invoked, at the
		// GET REST end-point "/" end-point, it does nothing and simply 
		// return what the REST end-point "/" is supposed to return,
		// the home page.
		Mockito.when(homeController.getHomePage()).thenReturn(homePage);

		// Test end-point, HTTP GET.
		// --------------
		// The productService.getHomePage() method is invoked at the GET REST
		// end-point "/".
		// The home page is returned in the "result" in the form of the
		// ResponseBody.
		webTestClient.get().uri("/").exchange().expectStatus().isOk().expectBodyList(String.class)
				.consumeWith(result -> {

					// Extract the home page from the ResponseBody.
					List<String> responseBody = result.getResponseBody();
					// Test the returned home.
					assertEquals(responseBody.get(0), homePage);

				});

		// Verify that the productService.getHomePage() method is invoked one time,
		// once.
		Mockito.verify(homeController, times(1)).getHomePage();
	}

}
