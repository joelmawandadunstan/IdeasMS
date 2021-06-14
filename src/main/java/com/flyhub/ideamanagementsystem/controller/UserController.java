package com.flyhub.ideamanagementsystem.controller; 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flyhub.ideamanagementsystem.entity.User;
import com.flyhub.ideamanagementsystem.service.CustomUserDetailsService;
import com.flyhub.ideamanagementsystem.service.UserServices;
  
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/users")
@EnableAutoConfiguration

public class UserController {

  @Autowired UserServices userService;
  

  @GetMapping
	public List<User> viewUsersList() {
		return userService.listAll();
	}
  
//  @GetMapping("/{id}")
//	public User viewParticularUser(@PathVariable("id") Long id, @RequestBody User user) {
//		return userService.getParticularUser(id, user);
//	}
  
  @PostMapping
  public User processRegistration(@RequestBody User user ) 
   { 
//	  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
//	  String encodedPassword = encoder.encode(user.getPassword()); 
//	  user.setPassword(encodedPassword);
//	  userRepo.save(user);
	  
//	  userService.saveUserWithDefaultRole(user); 
//	  user = userService.registerUser(user);
//	  
//	  String siteURL = Utility.getSiteURL(request);
//	  userService.sendVerificationEmail(user, siteURL); 
//	  
	  return userService.saveUserWithDefaultRole(user);
  
  }
  

	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable("id") Long id){
		userService.delete(id);	
	}
  
  @PatchMapping("/edit/{id}") 
  public User editUser(@PathVariable("id") Long id, @RequestBody User user) {
	  return userService.updateUser(id, user);
//	  return userService.get(id, user);
   }
  
//  @PostMapping("/save") 
//  public User saveUserEdit(User user) {
//	  return userService.saveUpdated(user);
//}
//  
//  @GetMapping("verify") public String verifyAccount(@Param("code") String
//  code, Model model) { 
//	  boolean verified = userService.verify(code);
//  
//	  String pageTitle = verified ? "Verification Succeeded!" :
//	  "Verification Failed"; model.addAttribute("pageTitle", pageTitle);
//  
//	  	return "register/" + (verified ? "verify_success" : "verify_fail"); 
//	  	}
  
  	@GetMapping("403")
  	public String error403() {
  		return "403";
  	}

  }
  
 
 