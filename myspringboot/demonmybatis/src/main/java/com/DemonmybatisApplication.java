package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.day.dao"})

public class DemonmybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemonmybatisApplication.class, args);
	}

}
