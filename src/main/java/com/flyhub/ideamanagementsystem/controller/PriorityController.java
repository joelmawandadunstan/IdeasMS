package com.flyhub.ideamanagementsystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flyhub.ideamanagementsystem.entity.PriorityEntity;
import com.flyhub.ideamanagementsystem.service.PriorityService;



@RestController
@RequestMapping("/api/v1/priorities")
public class PriorityController {

	@Autowired
	private PriorityService priorityService;
	
	@GetMapping("")
	public ResponseEntity<List<PriorityEntity>> listAllPriorities(){
		try {
			List<PriorityEntity> priorities = new ArrayList<PriorityEntity>();
			priorities.addAll(priorityService.listAllPriorities());
			if(priorities.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(priorities, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{priorityId}")
	public ResponseEntity<Optional<PriorityEntity>> getPriorityById(@PathVariable("priorityId") Long PriorityId){
		try {
			Optional<PriorityEntity> priority = priorityService.getPriorityById(PriorityId);
			return new ResponseEntity<>(priority, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("")
	public ResponseEntity<PriorityEntity> addPriority(@RequestBody PriorityEntity priorityEntity){
		try {
			PriorityEntity priority = priorityService.savePriority(priorityEntity);
			return new ResponseEntity<>(priority, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping("/{priorityId}")
    public ResponseEntity<PriorityEntity> partialUpdatePriority(@PathVariable("priorityId") Long priorityId, @RequestBody PriorityEntity priorityEntity) {
        Optional<Optional<PriorityEntity>> priority = Optional.ofNullable(priorityService.getPriorityById(priorityId));

        if (priority.isPresent()) {
            priorityEntity.setPriorityId(priorityId);
            return new ResponseEntity<>(priorityService.savePriority(priorityEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@PutMapping("/{priorityId}")
    public ResponseEntity<PriorityEntity> fullUpdatePriority(@PathVariable("priorityId") Long priorityId, @RequestBody PriorityEntity priorityEntity) {
        Optional<Optional<PriorityEntity>> priorityOptional = Optional.ofNullable(priorityService.getPriorityById(priorityId));

        if (priorityOptional.isPresent()) {
        	priorityEntity.setPriorityId(priorityId);
            return new ResponseEntity<>(priorityService.savePriority(priorityEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@DeleteMapping("/{priorityId}")
	public ResponseEntity<HttpStatus> deleteByPriorityId(@PathVariable("priorityId") Long priorityId){
		try {
			priorityService.deleteByPriorityId(priorityId);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping("")
	public ResponseEntity<HttpStatus> deleteAllPriorities(){
		try {
			priorityService.deleteAllPriorities();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
