package com.flyhub.ideamanagementsystem.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flyhub.ideamanagementsystem.entity.Idea;
import com.flyhub.ideamanagementsystem.repositories.IdeaRepository;


@Service
public class IdeaServices {
	
	@Autowired
	private IdeaRepository ideaRepo;
	
//	public void save (Idea idea) {
//		ideaRepo.save(idea);
//		
//	}
	
	//save Idea for the first time
	public Idea submitIdea(Idea idea) {
		
		return ideaRepo.save(idea);
		
	}
	
	public List<Idea> listAll(){
		return ideaRepo.findAll();
		
	}
	
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
	
	public void delete(Long id) {
		ideaRepo.deleteById(id);
	}
	
	
    public Idea get(Long id, Idea idea) {
        return ideaRepo.save(idea);
    }
    
    //save updated idea
//    public Idea saveUpdatedIdea(Idea idea) {
//		return ideaRepo.save(idea);
//		
//	}
    
    //save updated idea
//    public Idea saveUpdatedIdea(Idea idea) {
//    	Idea ideaToUpdate = get(idea.getId());
//    	ideaToUpdate.setIdea_title(idea.getIdea_title());
//    	ideaToUpdate.setIdea_description(idea.getIdea_description());
//		return ideaRepo.save(ideaToUpdate);
//		
//	}

}
