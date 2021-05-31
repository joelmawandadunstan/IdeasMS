package com.flyhub.ideamanagementsystem.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.flyhub.ideamanagementsystem.entity.Idea;
import com.flyhub.ideamanagementsystem.service.IdeaServices;

@RestController
@RequestMapping("api/v1/ideas")
@EnableAutoConfiguration

public class IdeaController {
	@Autowired
	private IdeaServices ideaService;

	@GetMapping
	public List<Idea> viewIdeasList() {
		return ideaService.listAll();
	}
	
	@PostMapping
	public Idea processSubmission(@RequestBody Idea idea)  {

		return ideaService.submitIdea(idea);
		
	}

	
	@DeleteMapping("/delete/{id}")
	public void deleteIdea(@PathVariable("id") Long id){
		ideaService.delete(id);	
	}
	
	
	@PatchMapping("/edit/{id}")
	public Idea editIdeas(@PathVariable("id") Long id, @RequestBody Idea idea) {
		return ideaService.get(id, idea);

		
	}
	
//	@PostMapping("/save/")
//	public Idea saveIdeaEdit(Idea idea) {
//		return ideaService.saveUpdatedIdea(idea);
//		
//	}
	


}
