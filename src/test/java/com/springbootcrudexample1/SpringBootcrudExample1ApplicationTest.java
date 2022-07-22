package com.springbootcrudexample1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springbootcrudexample1.controller.HomeController;

@SpringBootTest
class SpringBootcrudExample1ApplicationTest {

	@Autowired
	private HomeController controller;
	
	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}
