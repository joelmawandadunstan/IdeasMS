package com.flyhub.ideamanagementsystem.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flyhub.ideamanagementsystem.entity.Prefix;
import com.flyhub.ideamanagementsystem.repositories.PrefixRepository;

@Service
public class PrefixServices {
	
	 @Autowired
		private PrefixRepository prefixRepo;
	
	 public List<Prefix> listAll(){ 
		  return prefixRepo.findAll(); 
		  }
	 
	 public Prefix savePrefix(Prefix prefix) {
			
			return prefixRepo.save(prefix);
			}

}
