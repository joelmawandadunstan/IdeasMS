package com.flyhub.ideamanagementsystem.service;
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flyhub.ideamanagementsystem.CustomUserDetails;
import com.flyhub.ideamanagementsystem.entity.User;
import com.flyhub.ideamanagementsystem.repositories.UserRepository;

@Service 
public class CustomUserDetailsService implements UserDetailsService {
    
	  @Autowired 
	  private UserRepository userRepo;
	  
	  @Override 
	  @Transactional
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
		  User user = userRepo.findByUsername(username)
				  .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		  if (user== null) { 
			  throw new UsernameNotFoundException("User not found");
			  } 
		  return CustomUserDetails.build(user);
	  }
	  
  }
 