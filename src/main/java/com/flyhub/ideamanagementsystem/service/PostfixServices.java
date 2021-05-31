package com.flyhub.ideamanagementsystem.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flyhub.ideamanagementsystem.entity.Postfix;
import com.flyhub.ideamanagementsystem.repositories.PostfixRepository;

@Service
public class PostfixServices {
	
	@Autowired
	private PostfixRepository postfixRepo;

 public List<Postfix> listAll(){ 
	  return postfixRepo.findAll(); 
	  }

public Postfix savePostfix(Postfix postfix) {
	
	return postfixRepo.save(postfix);
	}


}
