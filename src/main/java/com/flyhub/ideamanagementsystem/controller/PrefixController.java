package com.flyhub.ideamanagementsystem.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.flyhub.ideamanagementsystem.entity.Prefix;
import com.flyhub.ideamanagementsystem.service.PrefixServices;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/prefix")
public class PrefixController {
	
	@Autowired private PrefixServices prefixService;
	
	@GetMapping
	public List<Prefix> viewPrefixList() {
		
		return prefixService.listAll();
		}
	
	@PostMapping
	public Prefix PrefixSave(@RequestBody Prefix prefix)  {

		return prefixService.savePrefix(prefix);
		
	}

}
