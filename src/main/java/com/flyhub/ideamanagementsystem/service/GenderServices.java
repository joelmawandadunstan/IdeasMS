package com.flyhub.ideamanagementsystem.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flyhub.ideamanagementsystem.entity.Gender;
import com.flyhub.ideamanagementsystem.repositories.GenderRepository;

@Service 
public class GenderServices {
	
	@Autowired
	private GenderRepository genderRepo;
	
	public List<Gender> listAll(){ 
		 return genderRepo.findAll(); 
	}

	public Gender saveGender(Gender gender) {
		
		return genderRepo.save(gender);
	}

}
