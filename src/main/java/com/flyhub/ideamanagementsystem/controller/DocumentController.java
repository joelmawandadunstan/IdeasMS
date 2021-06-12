package com.flyhub.ideamanagementsystem.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.flyhub.ideamanagementsystem.entity.Document;
import com.flyhub.ideamanagementsystem.entity.Idea;
import com.flyhub.ideamanagementsystem.message.ResponseFile;
import com.flyhub.ideamanagementsystem.message.ResponseMessage;
import com.flyhub.ideamanagementsystem.repositories.DocumentRepository;
import com.flyhub.ideamanagementsystem.service.DocumentServices;


@RestController
@RequestMapping("api/v1/documents")
@EnableAutoConfiguration
public class DocumentController {
	
	@Autowired
	private DocumentServices documentService;
	
	@Autowired
	private DocumentRepository documentRepo;
	
	@GetMapping
	public List<Document> viewDocumentList() {
		return documentService.listAll();
	}
	
//	
//	@PostMapping
//	public Document processSubmission(@RequestBody Document document)  {
//
//		return documentService.submitDocument(document);
//		
//	}
//	
//	@GetMapping("/download")
//	public void downloadFile(@Param("id") Long id, HttpServletResponse response) throws Exception{
//		Optional<Document> result = documentRepo.findById(id);
//		if (!result.isPresent()) {
//			throw new Exception("Could not Find Document with ID: " + id);
//		}
//		
//		Document document = result.get();
//		response.setContentType("application/octet-stream");
//		String headerKey = "Content-Disposition";
//		String headerValue = "attachment; filename=" + document.getName();
//		
//		response.setHeader(headerKey, headerValue);
//		
//		ServletOutputStream outputStream =  response.getOutputStream();
//		
//		outputStream.write(document.getContent());
//		outputStream.close();
//		}
//	
//	
	@DeleteMapping("/delete/{id}")
	public void deleteDocument(@PathVariable("id") Long id){
		documentService.delete(id);	
	}
	
	@PatchMapping("/edit/{id}")
	public Document editDocuments(@PathVariable("id") Long id, @RequestBody Document document) {
		return documentService.updateDocument(id, document);

		
	}
	
	 @PostMapping
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    try {
	    	documentService.store(file);

	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }
	 
	 //Download
//	  @GetMapping("/download")
//	  public ResponseEntity<List<ResponseFile>> getListFiles() {
//	    List<ResponseFile> files = documentService.getAllFiles().map(dbFile -> {
//	      String fileDownloadUri = ServletUriComponentsBuilder
//	          .fromCurrentContextPath()
//	          .path("/files/")
//	          .path(dbFile.getId())
//	          .toUriString();
//
//	      return new ResponseFile(
//	          dbFile.getName(),
//	          fileDownloadUri,
//	          dbFile.getType(),
//	          dbFile.getData().length);
//	    }).collect(Collectors.toList());
//
//	    return ResponseEntity.status(HttpStatus.OK).body(files);
//	  }
//
//	  @GetMapping("/{id}")
//	  public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
//	    Document document = documentService.getFile(id);
//
//	    return ResponseEntity.ok()
//	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"")
//	        .body(document.getData());
//	  }
//	}
	 
	 
	//Old ones
//	@GetMapping("/documents")
//	public String document_form(Model model) {
//		List<Document> listDocs = documentRepo.findAll();
//		//model.addAttribute("document", new Document());
//		model.addAttribute("listDocs", listDocs);
//		return "document_form";
//		}
	
//	@PostMapping("/upload")
//	public String uploadFile(@RequestParam("document") MultipartFile multipartFile,
//			RedirectAttributes ra ) throws IOException{
//		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//		
//		Document document = new Document();
//		document.setName(fileName);
//		document.setContent(multipartFile.getBytes());
//		document.setSize(multipartFile.getSize());
//		document.setUploadTime(new Date());
//		
//		documentRepo.save(document);
//		
//		ra.addFlashAttribute("message", "The file has been uploaded successfully.");
//		
//		return "redirect:/documents";
//	}

//	@GetMapping("/download")
//	public void downloadFile(@Param("id") Long id, HttpServletResponse response) throws Exception{
//		Optional<Document> result = documentRepo.findById(id);
//		if (!result.isPresent()) {
//			throw new Exception("Could not Find Document with ID: " + id);
//		}
//		
//		Document document = result.get();
//		response.setContentType("application/octet-stream");
//		String headerKey = "Content-Disposition";
//		String headerValue = "attachment; filename=" + document.getName();
//		
//		response.setHeader(headerKey, headerValue);
//		
//		ServletOutputStream outputStream =  response.getOutputStream();
//		
//		outputStream.write(document.getContent());
//		outputStream.close();
//	}

}
