package com.flyhub.ideamanagementsystem.controller;
  
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flyhub.ideamanagementsystem.Utility;
import com.flyhub.ideamanagementsystem.entity.Idea;
import com.flyhub.ideamanagementsystem.entity.Role;
import com.flyhub.ideamanagementsystem.entity.User;
import com.flyhub.ideamanagementsystem.repositories.CategoryRepository;
import com.flyhub.ideamanagementsystem.repositories.GenderRepository;
import com.flyhub.ideamanagementsystem.repositories.PostfixRepository;
import com.flyhub.ideamanagementsystem.repositories.PrefixRepository;
import com.flyhub.ideamanagementsystem.repositories.UserRepository;
import com.flyhub.ideamanagementsystem.service.UserServices;
  
  @Controller
  @EnableAutoConfiguration public class UserController {
  
  
  
  @Autowired private UserServices userService;
  
  
  @Autowired private UserRepository userRepo;
  
  @Autowired private PostfixRepository postfixRepo;
  
  @Autowired private PrefixRepository prefixRepo;
  
  @Autowired
	private GenderRepository genderRepo;
  
//  @GetMapping("/") public String viewDash () { 
//	  return "login"; 
//	  }
  
  @GetMapping("/") public String viewHomePage () { 
	  return "index"; 
	  }
  
  @GetMapping("/logout") public String logOut () { 
	  return "index"; 
	  }
  
  @GetMapping("/register") public String showSignUpForm(Model model) {
  model.addAttribute("user", new User()); 
  model.addAttribute("genders", genderRepo.findAll());
  model.addAttribute("postfixs", postfixRepo.findAll());
  model.addAttribute("prefixs", prefixRepo.findAll());
  	return "signup_form"; 
  }
  
  @PostMapping("/process_register") 
  public String processRegistration(User user, HttpServletRequest request) throws 
  UnsupportedEncodingException, MessagingException { 
	  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
	  String encodedPassword = encoder.encode(user.getPassword()); 
	  user.setPassword(encodedPassword);
	  userRepo.save(user);
	  
	  userService.saveUserWithDefaultRole(user); 
	  userService.registerUser(user);
	  
	  String siteURL = Utility.getSiteURL(request);
	  userService.sendVerificationEmail(user, siteURL); 
	  
	  return "register_success";
  
  }
  
//  @GetMapping("/list_users") 
//  public String viewUsersList(Model model) { 
//	  List <User> listUsers = userService.listAll();
//	  model.addAttribute("listUsers",listUsers);		  
//	  return "users";
//  }
  
  //pagination
  @GetMapping("/list_users")
	public String viewUsersList(Model model) {
//	public String viewIdeasList(Model model, 
//			@Param("keyword") String keyword) {
//		List <Idea> listIdeas = ideaService.listAll(keyword);
//		model.addAttribute("listIdeas", listIdeas);
//		model.addAttribute("keyword", keyword);
		//return "ideas";s
		String keyword = null;
		return listByPage(model, 1, "id", "asc", keyword);	
	}
  
  //pagination
  @GetMapping("/pages/{pageNumber}")
	public String listByPage(Model model, 
			@PathVariable("pageNumber") int currentPage,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir,
			@Param("keyword") String keyword) {
		
		Page <User> page = userService.listAll(currentPage, sortField, sortDir, keyword);
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		List <User> listUsers = page.getContent();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("keyword", keyword);
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);
		//model.addAttribute("keyword", keyword);
		return "users";
	}
  
  @RequestMapping("/users/delete/{id}") 
  public String deleteUser(@PathVariable("id") Long id){ 
	  userService.delete(id); 
	  return "redirect:/list_users";
   }
  
  //to do list
  
  @GetMapping("/users/edit/{id}") 
  public String editUser(@PathVariable("id")Long id, Model model) { 
	  User user = userService.get(id); List<Role> listRoles = userService.getRoles();
	  model.addAttribute("user", user); model.addAttribute("listRoles", listRoles);
	  
	  return "user_form";
  
  
  
   }
  
  @PostMapping("/users/save") 
  public String saveUser (User user) {
  userService.save(user); 
  
  	return "redirect:/list_users";
  
  }
  
  @GetMapping("/verify") public String verifyAccount(@Param("code") String
  code, Model model) { 
	  boolean verified = userService.verify(code);
  
	  String pageTitle = verified ? "Verification Succeeded!" :
	  "Verification Failed"; model.addAttribute("pageTitle", pageTitle);
  
	  	return "register/" + (verified ? "verify_success" : "verify_fail"); 
	  	}
  
  	@GetMapping("/403")
  	public String error403() {
  		return "403";
  	}
//  
//  @GetMapping("/newindex")
//  public String showIndexPage() {
//	  return "index";
//  }
//  
//  @GetMapping("/newindex1")
//  public String showIdeas() {
//	  return "ideas1";
//  }
  }
  
 
 