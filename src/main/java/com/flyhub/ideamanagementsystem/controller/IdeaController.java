package com.flyhub.ideamanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flyhub.ideamanagementsystem.entity.Idea;
import com.flyhub.ideamanagementsystem.repositories.CategoryRepository;
import com.flyhub.ideamanagementsystem.repositories.IdeaRepository;
import com.flyhub.ideamanagementsystem.repositories.PriorityRepository;
import com.flyhub.ideamanagementsystem.service.IdeaServices;

@Controller
@EnableAutoConfiguration

public class IdeaController {
	@Autowired
	private IdeaServices ideaService;
	
	@Autowired
	private IdeaRepository ideaRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private PriorityRepository priorityRepo;
	
	@GetMapping("/ideas")
	public String idea_form(Model model) {
		model.addAttribute("idea", new Idea());
		model.addAttribute("categories", categoryRepo.findAll());
		model.addAttribute("priorities", priorityRepo.findAll());
		return "idea_form";
		}
	
	@PostMapping("/idea_submission")
	public String processSubmission(Idea idea)  {

		ideaService.submitIdea(idea);
		
		return "idea_submission_success";
		
	}
	
	@GetMapping("/list_ideas")
	public String viewIdeasList(Model model) {
//	public String viewIdeasList(Model model, 
//			@Param("keyword") String keyword) {
//		List <Idea> listIdeas = ideaService.listAll(keyword);
//		model.addAttribute("listIdeas", listIdeas);
//		model.addAttribute("keyword", keyword);
		//return "ideas";s
		String keyword = null;
		return listByPage(model, 1, "id", "asc", keyword);	
	}
	
	@GetMapping("/page/{pageNumber}")
	public String listByPage(Model model, 
			@PathVariable("pageNumber") int currentPage,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir,
			@Param("keyword") String keyword) {
		
		Page <Idea> page = ideaService.listAll(currentPage, sortField, sortDir, keyword);
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		List <Idea> listIdeas = page.getContent();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("listIdeas", listIdeas);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("keyword", keyword);
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);
		//model.addAttribute("keyword", keyword);
		return "ideas";
	}
	
	@RequestMapping("/ideas/delete/{id}")
	public String deleteIdea(@PathVariable("id") Long id){
		ideaService.delete(id);
		return "redirect:/list_ideas";	
	}
	
	
	@GetMapping("/ideas/edit/{id}")
	public String editUserRoles(@PathVariable("id") Long id, Model model) {
		Idea idea = ideaService.get(id);
		model.addAttribute("idea", idea);
		return "idea_edit_form";
	}
	
	@PostMapping("/ideas/save/")
	public String saveIdeaEdit(Idea idea) {
		ideaService.saveUpdatedIdea(idea);
		return "redirect:/list_ideas";
	}
	
//	//Category lookup
//	@GetMapping("/ideas")
//	//@GetMapping("/ideas")
//	public String showIdeaScrean(Model model) {
//		model.addAttribute("idea", new Idea());
//		model.addAttribute("categories",categoryRepo.findAll());
//		model.addAttribute("priorities",priorityRepo.findAll());
//		//return "ideas";
//		return "idea_form";
//	}

}
