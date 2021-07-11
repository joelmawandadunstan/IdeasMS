package com.flyhub.ideamanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class IdeamanagementsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdeamanagementsystemApplication.class, args);
		System.out.println(" Welcome to Ideas");
	}
	
}
