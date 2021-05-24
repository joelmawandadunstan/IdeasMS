package com.flyhub.ideamanagementsystem.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flyhub.ideamanagementsystem.entity.Idea;
import com.flyhub.ideamanagementsystem.entity.Notes;
import com.flyhub.ideamanagementsystem.entity.User;
import com.flyhub.ideamanagementsystem.repositories.IdeaRepository;
import com.flyhub.ideamanagementsystem.repositories.NotesRepository;
import com.flyhub.ideamanagementsystem.service.NotesServices;

@Controller
public class NotesController {
	
	@Autowired
	private NotesServices notesService;
	
	@Autowired
	private NotesRepository notesRepo;
	
	@Autowired
	IdeaRepository ideaRepo;
	
	
	//@GetMapping("/notes/add/{id}")
	@GetMapping("/notes")
	//public String notes_form(@PathVariable Long id, Model model) {
	public String notes_form(Model model) {
		model.addAttribute("notes", new Notes());
		//model.addAttribute("ideaId", id);
		return "notes_form";
		
		}
	
	@PostMapping("/notes_submission")
	public String processSubmission(Notes notes)  {

		notesService.submitNotes(notes);
		
		return "notes_submission_success";
		
	}
	
	//@GetMapping("/list_notes")
	//public String viewNotesList(Model model) {
		//List <Notes> listNotes = notesService.listAll();
		//model.addAttribute("listNotes", listNotes);
		//return "notes";
	//}
	
	 //pagination
	  @GetMapping("/list_notes")
		public String viewNotesList(Model model) {
//		public String viewIdeasList(Model model, 
//				@Param("keyword") String keyword) {
//			List <Idea> listIdeas = ideaService.listAll(keyword);
//			model.addAttribute("listIdeas", listIdeas);
//			model.addAttribute("keyword", keyword);
			//return "ideas";s
			String keyword = null;
			return listByPage(model, 1, "id", "asc", keyword);	
		}
	  
	  //pagination
	  @GetMapping("/pagez/{pageNumber}")
		public String listByPage(Model model, 
				@PathVariable("pageNumber") int currentPage,
				@Param("sortField") String sortField,
				@Param("sortDir") String sortDir,
				@Param("keyword") String keyword) {
			
			Page <Notes> page = notesService.listAll(currentPage, sortField, sortDir, keyword);
			long totalItems = page.getTotalElements();
			int totalPages = page.getTotalPages();
			List <Notes> listNotes = page.getContent();
			
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("totalItems", totalItems);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("listNotes", listNotes);
			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDir", sortDir);
			model.addAttribute("keyword", keyword);
			
			String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
			model.addAttribute("reverseSortDir", reverseSortDir);
			//model.addAttribute("keyword", keyword);
			return "notes";
		}
	
	@RequestMapping("/notes/delete/{id}")
	public String deleteNotes(@PathVariable("id") Long id){
		notesService.delete(id);
		return "redirect:/list_notes";	
	}
	
	@GetMapping("/notes/edit/{id}")
	public String editNotes(@PathVariable("id") Long id, Model model) {
		Notes notes = notesService.get(id);
		model.addAttribute("notes", notes);
		return "notes_edit_form";
	}
	
	@PostMapping("/notes/save/")
	public String saveNotesEdit(Notes notes) {
		notesService.saveUpdatedNotes(notes);
		return "redirect:/list_notes";
	}
	
	
	//mapping
//	@GetMapping("/notes/{id}")
//	public String showAddNotesForm(@PathVariable("id") long id,Model model) {
//		Idea idea = ideaRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Idea Id:" + id));
//		model.addAttribute("idea",idea);
//		model.addAttribute("notes", new Notes());
//		return "notes_form";
//	}
	
//	@PostMapping("/notes/save/{id}")
//	public String saveNote(Notes note, @PathVariable("id") long id,RedirectAttributes redirectAttributes) {
//		Idea idea = ideaRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Idea Id: "+id));
//		
//		notesRepo.save(
//				new Notes(idea,note.getNoteContent())
//				);
//		
//		redirectAttributes.addFlashAttribute("message", "Your Note has been saved Successfully.");
//		return "redirect:/list_notes";
//	}
}