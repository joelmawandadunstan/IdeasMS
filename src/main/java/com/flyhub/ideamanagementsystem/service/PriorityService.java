package com.flyhub.ideamanagementsystem.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flyhub.ideamanagementsystem.entity.PriorityEntity;
import com.flyhub.ideamanagementsystem.repositories.PriorityRepository;


@Service
public class PriorityService {

	@Autowired 
	private PriorityRepository priorityRepository;
	
	public List<PriorityEntity> listAllPriorities(){
		return priorityRepository.findAll();
	}
	
	public Optional<PriorityEntity> getPriorityById(Long priorityId) {
		return priorityRepository.findById(priorityId);
	}
	
	public PriorityEntity savePriority(PriorityEntity priority) {
		return priorityRepository.save(priority);
	}
	
	public void deleteByPriorityId(Long priorityId) {
		priorityRepository.deleteById(priorityId);
	}
	
	public void deleteAllPriorities() {
		priorityRepository.deleteAll();
	}
}
