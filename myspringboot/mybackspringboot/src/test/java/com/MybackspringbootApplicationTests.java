package com;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class MybackspringbootApplicationTests {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ApplicationContext context;
	@Test
	void contextLoads() {
		assertNotNull(context.getBean("productController"));
		assertNotNull(context.getBean("orderController"));
		assertNotNull(context.getBean("customerController"));
	}

}
