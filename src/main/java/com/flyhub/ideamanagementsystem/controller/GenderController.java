package com.flyhub.ideamanagementsystem.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.flyhub.ideamanagementsystem.entity.Gender;
import com.flyhub.ideamanagementsystem.service.GenderServices;


//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/gender")

  @EnableAutoConfiguration
public class GenderController {
	
	 @Autowired private GenderServices genderService;
	 
	 
	
	@GetMapping
	public List<Gender> viewGenderList() {
		
		return genderService.listAll();
		}
	
	@PostMapping
	public Gender GenderSave(@RequestBody Gender gender)  {

		return genderService.saveGender(gender);
		
	}

}
