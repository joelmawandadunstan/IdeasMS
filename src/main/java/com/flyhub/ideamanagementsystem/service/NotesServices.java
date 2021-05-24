package com.flyhub.ideamanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.flyhub.ideamanagementsystem.entity.Idea;
import com.flyhub.ideamanagementsystem.entity.Notes;
import com.flyhub.ideamanagementsystem.entity.User;
import com.flyhub.ideamanagementsystem.repositories.IdeaRepository;
import com.flyhub.ideamanagementsystem.repositories.NotesRepository;


@Service
public class NotesServices {
	
	@Autowired
	private NotesRepository notesRepo;
	
//	public void save (Idea idea) {
//		notesRepo.save(notes);
//		
//	}
	
	//save Notes for the first time
		public Notes submitNotes(Notes notes) {
			
			return notesRepo.save(notes);
		}
	
	
//	  public Notes submitNotes(Notes notes, Idea idea) { Long idea_id =
//	  notes.getIdea_id(); notes.setIdea_id(idea_id); return notesRepo.save(notes);
//	  
//	  }
	 	
	 //pagination
	  public Page<Notes> listAll(int pageNumber, String sortField, String sortDir,
				String keyword){
			Sort sort = Sort.by(sortField);
			sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
			Pageable pageable = PageRequest.of(pageNumber -1, 5, sort);
			
			if (keyword != null ) {
				return notesRepo.findAll(keyword, pageable);
				}
			return notesRepo.findAll(pageable);
		}
	
//	 public List<Notes> listAll(){ 
		 // return notesRepo.findAll(); 
		 // }
	 
	 public void delete(Long id) {
			notesRepo.deleteById(id);
		}
	 
	 //return single note for edit
	    public Notes get(Long id) {
	        return notesRepo.findById(id).get();
	    }
	    
	    //save updated Notes
	    public Notes saveUpdatedNotes(Notes notes) {
			return notesRepo.save(notes);
			
		}
	 
	
//	//public Page<Idea> listAll(String keyword){
//		//if (keyword != null ) {
//		//return ideaRepo.findAll(keyword);
//	//}
//	//return ideaRepo.findAll();
//	public Page<Idea> listAll(int pageNumber, String sortField, String sortDir,
//			String keyword){
//		Sort sort = Sort.by(sortField);
//		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
//		Pageable pageable = PageRequest.of(pageNumber -1, 5, sort);
//		
//		if (keyword != null ) {
//			return ideaRepo.findAll(keyword, pageable);
//			}
//		return ideaRepo.findAll(pageable);
//	}
//	
//	public void delete(Long id) {
//		ideaRepo.deleteById(id);
//	}
//	
//	 //return single idea for edit
//    public Idea get(Long id) {
//        return ideaRepo.findById(id).get();
//    }

}
