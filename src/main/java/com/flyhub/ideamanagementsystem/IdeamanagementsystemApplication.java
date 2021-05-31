package com.flyhub.ideamanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class IdeamanagementsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdeamanagementsystemApplication.class, args);
		System.out.println(" Welcome to Ideas");
	}
	
	@Bean
	public WebMvcConfigurer Configure() {
	return new WebMvcConfigurer() {

	 

	// @Override
	public void addCorsMappings(CorsRegistry registry) {
	registry
	.addMapping("/*")
	.allowedMethods("POST", "GET", "PUT", "DELETE")
	.allowedHeaders("*")
	.allowedOrigins("*");
	// .exposedHeaders("Content-Type")
	// .allowCredentials(true).maxAge(3600);
	}
	};
	}

}
