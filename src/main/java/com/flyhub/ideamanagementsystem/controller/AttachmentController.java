package com.flyhub.ideamanagementsystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.flyhub.ideamanagementsystem.entity.AttachmentEntity;
import com.flyhub.ideamanagementsystem.message.ResponseMessage;
import com.flyhub.ideamanagementsystem.service.AttachmentServices;

@RestController
@RequestMapping("api/v1/Attachments")
@EnableAutoConfiguration
public class AttachmentController {
	
	@Autowired
	private AttachmentServices attachmentService;
	
	@GetMapping
	public List<AttachmentEntity> viewAttachmentList() {
		return attachmentService.listAll();
	}
	

	@DeleteMapping("/delete/{id}")
	public void deleteAttachment(@PathVariable("id") Long id){
		attachmentService.delete(id);	
	}
	
	@PatchMapping("/edit/{id}")
	public AttachmentEntity editAttachments(@PathVariable("id") Long id, @RequestBody AttachmentEntity attachment) {
		return attachmentService.updateAttachment(id, attachment);

		
	}
	
	 @PostMapping
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    try {
	    	attachmentService.store(file);

	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }
}
