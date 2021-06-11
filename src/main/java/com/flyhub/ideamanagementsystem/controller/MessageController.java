package com.flyhub.ideamanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flyhub.ideamanagementsystem.service.MessageServices;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("api/v1/message")
public class MessageController {
	
	@Autowired	
	private MessageServices messageServices;
	@GetMapping
	public String ShowMessage() {
		//messageServices.findMessage(message);
		return "Put Your Ideas to Life";
		
		}

}
