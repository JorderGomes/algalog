package com.jorder.entregas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
//@ComponentScan(basePackages = {"com.jorder"})
public class EntregasApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntregasApplication.class, args);
		System.out.println("Api Is Up!");
	}
}
