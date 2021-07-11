package com.flyhub.ideamanagementsystem.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flyhub.ideamanagementsystem.entity.IdeaEntity;
import com.flyhub.ideamanagementsystem.repositories.IdeaRepository;


@Service
public class IdeaServices {
	
	@Autowired
	private IdeaRepository ideaRepository;
	
	public IdeaEntity submitIdea(IdeaEntity idea) {
		
		return ideaRepository.save(idea);		
	}
	
	public List<IdeaEntity> listAll(){
		return ideaRepository.findAll();	
	}
	
	public void delete(Long id) {
		ideaRepository.deleteById(id);
	}
		
    public IdeaEntity get(Long id, IdeaEntity idea) {
        return ideaRepository.save(idea);
    }
}
