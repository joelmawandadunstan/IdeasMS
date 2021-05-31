package com.flyhub.ideamanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flyhub.ideamanagementsystem.entity.Message;
import com.flyhub.ideamanagementsystem.entity.Notes;
import com.flyhub.ideamanagementsystem.repositories.MessageRepository;
import com.flyhub.ideamanagementsystem.repositories.NotesRepository;

@Service
public class MessageServices {
	@Autowired
	private MessageRepository messageRepo;
	
	//save Message 
			public Message findMessage(Message message) {
				
				return messageRepo.save(message);
			}

}
