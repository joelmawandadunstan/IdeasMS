package com.flyhub.ideamanagementsystem.service;  
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.flyhub.ideamanagementsystem.entity.Role;
import com.flyhub.ideamanagementsystem.entity.User;
import com.flyhub.ideamanagementsystem.repositories.RoleRepository;
import com.flyhub.ideamanagementsystem.repositories.UserRepository;

  @Service 
  public class UserServices {
  
  @Autowired private UserRepository userRepo;
  
  @Autowired JavaMailSender mailSender;
  
  @Autowired private RoleRepository roleRepo;
  
//  public User RegisterUser(User user) { 
//	  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
//	  String encodedPassword = encoder.encode(user.getPassword()); 
//	  user.setPassword(encodedPassword);
//	  
//	  String randomCode = RandomString.make(64);
//	  user.setVerificationCode(randomCode);
//  
//	  return userRepo.save(user);
//  
//  }
  
  //pagination
//  public Page<User> listAll(int pageNumber, String sortField, String sortDir,
//			String keyword){
//		Sort sort = Sort.by(sortField);
//		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
//		Pageable pageable = PageRequest.of(pageNumber -1, 5, sort);
//		
//		if (keyword != null ) {
//			return userRepo.findAll(keyword, pageable);
//			}
//		return userRepo.findAll(pageable);
//	}
  
//  public void sendVerificationEmail(User user, String siteURL) throws
//  UnsupportedEncodingException, MessagingException { 
//	  String subject = "Please verify your registration"; String senderName = "Ideas Team"; String
//	  mailContent ="<p>Dear " + user.getFirstName() + ",</p>"; mailContent +=
//	  "<p>Please click the link below to verify your registration</p>";
//	  
//	  String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
//	  mailContent +="<h3><a href=\" " + verifyURL + "\">VERIFY</a></h3>";
//	  
//	  mailContent += "<p>Thank You<br>The Ideas Team</p>";
//	  
//	  MimeMessage message = mailSender.createMimeMessage(); MimeMessageHelper
//	  helper = new MimeMessageHelper(message);
//	  
//	  helper.setFrom("mawandajoel20@gmail.com", senderName);
//	  helper.setTo(user.getEmail()); helper.setSubject(subject);
//	  
//	  helper.setText(mailContent, true);
//  
//	  mailSender.send(message); 
//	  
//  }
  
//  public boolean verify(String verificationCode) { 
//	  User user = userRepo.findByVerificationCode(verificationCode);
//  
//  return true;
//  
//  }
  
  public User saveUserWithDefaultRole(User user) { 
	  BCryptPasswordEncoder
	  encoder = new BCryptPasswordEncoder(); String encodedPassword =
	  encoder.encode(user.getPassword()); user.setPassword(encodedPassword);
	  
	  Role roleUser = roleRepo.findByName("User"); user.addRole(roleUser);
	  
	 return  userRepo.save(user); }
	  
  public User saveUpdated (User user) { BCryptPasswordEncoder encoder = new
	  BCryptPasswordEncoder(); String encodedPassword =
	  encoder.encode(user.getPassword()); user.setPassword(encodedPassword);
	   return userRepo.save(user);
  
  }
  
  public List<User> listAll(){ 
	  return userRepo.findAll(); 
	  }
  
  public void delete(Long id) { 
	  userRepo.deleteById(id); 
	  }
  
//  public User get(Long id, User user) { 	  
//	  return userRepo.save(user);
//  
//  }
  
  public User updateUser(Long id, User user) {
	  User foundUser = userRepo.findById(id).get();
	  foundUser.setId(user.getId());
	  foundUser.setUsername(user.getUsername());
	  foundUser.setEmail(user.getEmail());
	  //foundUser.setRoles(user.getRoles());
	 
	  return userRepo.save(foundUser);
	  }
  
  public List<Role> getRoles(){ 
	  return roleRepo.findAll(); 
	  }
  
  }
 