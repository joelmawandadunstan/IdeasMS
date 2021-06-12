package com.flyhub.ideamanagementsystem.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.flyhub.ideamanagementsystem.entity.Document;
import com.flyhub.ideamanagementsystem.repositories.DocumentRepository;

@Service 
public class DocumentServices {
	
	@Autowired
	private DocumentRepository documentRepo;
	
	//Upload files
//		public Document submitDocument(Document document) {
//			
//			return documentRepo.save(document);
//			}
//		
		public List<Document> listAll(){
			return documentRepo.findAll();
			
		}
//		
		public void delete(Long id) {
			documentRepo.deleteById(id);
		}
		
		public Document updateDocument(Long id, Document document) {
	        return documentRepo.save(document);
	    }
			

	
	public Document store(MultipartFile file) throws IOException {
	    String name = StringUtils.cleanPath(file.getOriginalFilename());
	    Document document = new Document();
	    document.setName(name);
	    document.setSize(file.getSize());
	    document.setContent(file.getBytes());
	    	
	    return documentRepo.save(document);
	  }

	  public Document getFile(Long id) {
	    return documentRepo.findById(id).get();
	  }
	  
	  public Stream<Document> getAllFiles() {
	    return documentRepo.findAll().stream();
	  }
	}
