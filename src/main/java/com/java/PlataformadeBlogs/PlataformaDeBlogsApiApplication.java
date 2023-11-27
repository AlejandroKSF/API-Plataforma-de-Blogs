package com.java.PlataformadeBlogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class PlataformaDeBlogsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlataformaDeBlogsApiApplication.class, args);
	}
}
