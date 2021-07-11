package com.flyhub.ideamanagementsystem.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.flyhub.ideamanagementsystem.entity.AttachmentEntity;
import com.flyhub.ideamanagementsystem.repositories.AttachmentRepository;

@Service 
public class AttachmentServices {
	
	@Autowired
	private AttachmentRepository attachmentRepository;
		
		public List<AttachmentEntity> listAll(){
			return attachmentRepository.findAll();
			
		}
		
		public void delete(Long id) {
			attachmentRepository.deleteById(id);
		}
		
		public AttachmentEntity updateAttachment(Long id, AttachmentEntity attachment) {
	        return attachmentRepository.save(attachment);
	    }
			

	
	public AttachmentEntity store(MultipartFile file) throws IOException {
	    String name = StringUtils.cleanPath(file.getOriginalFilename());
	    AttachmentEntity attachment = new AttachmentEntity();
	    attachment.setName(name);
	    attachment.setSize(file.getSize());
	    attachment.setContent(file.getBytes());
	    	
	    return attachmentRepository.save(attachment);
	  }

	  public AttachmentEntity getFile(Long id) {
	    return attachmentRepository.findById(id).get();
	  }
	  
	  public Stream<AttachmentEntity> getAllFiles() {
	    return attachmentRepository.findAll().stream();
	  }
	}
