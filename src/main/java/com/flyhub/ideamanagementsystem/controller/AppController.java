package com.flyhub.ideamanagementsystem.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.flyhub.ideamanagementsystem.Utility;
import com.flyhub.ideamanagementsystem.entity.Role;
import com.flyhub.ideamanagementsystem.entity.User;
import com.flyhub.ideamanagementsystem.repository.UserRepository;
import com.flyhub.ideamanagementsystem.service.UserServices;

@Controller
@EnableAutoConfiguration
public class AppController {
	

	
	@Autowired
	private UserServices service;
	
	@Autowired
	private UserRepository repo;

	@GetMapping("/")
	public String viewHomePage () {
		return "index";
	}
	
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
		
	}
	
	@PostMapping("/process_register")
	public String processRegistration(User user, HttpServletRequest request) 
			throws UnsupportedEncodingException, MessagingException {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String encodedPassword = encoder.encode(user.getPassword());
//		user.setPassword(encodedPassword);
//		repo.save(user);
		
		service.saveUserWithDefaultRole(user);
		service.registerUser(user);
		
		//String siteURL = Utility.getSiteURL(request);
		//service.sendVerificationEmail(user, siteURL);
		return "register_success";
		
	}
	
	@GetMapping("/list_users")
	public String viewUsersList(Model model) {
		List <User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
		
	}
		
@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = service.get(id);
		List<Role> listRoles = service.getRoles();
		
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		
		
		return "user_form";
	}

	@PostMapping("/users/save")
	public String saveUser (User user) {
		service.save(user);
		return "redirect:/list_users";
		
	}
	
	@GetMapping("/verify")
	public String verifyAccount(@Param("code") String code, Model model) {
		boolean verified = service.verify(code);
		
		String pageTitle = verified ? "Verification Succeeded!" : "Verification Failed";
		model.addAttribute("pageTitle", pageTitle);
		
		return "register/" + (verified ? "verify_success" : "verify_fail");
	}
	

}
