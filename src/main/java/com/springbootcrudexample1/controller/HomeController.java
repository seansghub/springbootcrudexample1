package com.springbootcrudexample1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h2>This class provides the HTTP end-points for the Home Page.</h2>
 * Display the home page for this website with all the relevant informations, texts and images.
 * 
 * @author seanea
 * @version 1.0
 * @since 2022-06-29
 *
 */
@RestController
@RequestMapping("/")
public class HomeController {
	/**
	 * This end-point method gets the home page of this application.
	 * 
	 * @return The home page.
	 */
	@GetMapping
	public String getHomePage() {
		// TODO
		return "Hello, this is the product home page";
	}
}
