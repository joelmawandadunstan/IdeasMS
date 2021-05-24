package com.flyhub.ideamanagementsystem.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.flyhub.ideamanagementsystem.entity.Document;
import com.flyhub.ideamanagementsystem.repositories.DocumentRepository;

@Controller
public class DocumentController {
	
	@Autowired
	private DocumentRepository documentRepo;
	
	@GetMapping("/documents")
	public String document_form(Model model) {
		List<Document> listDocs = documentRepo.findAll();
		//model.addAttribute("document", new Document());
		model.addAttribute("listDocs", listDocs);
		return "document_form";
		}
	
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("document") MultipartFile multipartFile,
			RedirectAttributes ra ) throws IOException{
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		Document document = new Document();
		document.setName(fileName);
		document.setContent(multipartFile.getBytes());
		document.setSize(multipartFile.getSize());
		document.setUploadTime(new Date());
		
		documentRepo.save(document);
		
		ra.addFlashAttribute("message", "The file has been uploaded successfully.");
		
		return "redirect:/documents";
	}

	@GetMapping("/download")
	public void downloadFile(@Param("id") Long id, HttpServletResponse response) throws Exception{
		Optional<Document> result = documentRepo.findById(id);
		if (!result.isPresent()) {
			throw new Exception("Could not Find Document with ID: " + id);
		}
		
		Document document = result.get();
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + document.getName();
		
		response.setHeader(headerKey, headerValue);
		
		ServletOutputStream outputStream =  response.getOutputStream();
		
		outputStream.write(document.getContent());
		outputStream.close();
	}

}
