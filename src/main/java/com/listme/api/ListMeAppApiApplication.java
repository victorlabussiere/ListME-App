package com.listme.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ListMeAppApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListMeAppApiApplication.class, args);
	}

}
