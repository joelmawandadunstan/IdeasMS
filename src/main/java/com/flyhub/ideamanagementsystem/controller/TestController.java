package com.flyhub.ideamanagementsystem.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/User")
	@PreAuthorize("hasRole('User') or hasRole('Customer') or hasRole('Admin')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/Customer")
	@PreAuthorize("hasRole('Customer')")
	public String moderatorAccess() {
		return "Customer Board.";
	}

	@GetMapping("/Admin")
	@PreAuthorize("hasRole('Admin')")
	public String adminAccess() {
		return "Admin Board.";
	}

}
