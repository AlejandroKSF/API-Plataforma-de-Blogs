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
	@Configuration
	public class SwaggerConfig {
	  @Bean
	  public OpenAPI financasOpenAPI() {
	     return new OpenAPI().info(new Info()
	        .title("API de uma Plataforma de Blogs: API que controla os posts e os comentários de um blog.")
	        .description("Esta API é utilizada na disciplina Desenvolvimento para Servidores-II")
	        .version("v0.0.1")
	        .contact(new Contact()
	          .name("Alejandro Farias e Leonardo Serqueira").email("alejandro.farias@fatec.sp.gov.br;leonardo.serqueira@fatec.sp.gov.br"))
	        .license(new License()
	          .name("Apache 2.0").url("http://springdoc.org")));
	  }
	}
}
