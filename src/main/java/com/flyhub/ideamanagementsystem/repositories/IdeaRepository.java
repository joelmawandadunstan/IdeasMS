package com.flyhub.ideamanagementsystem.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.flyhub.ideamanagementsystem.entity.Idea;

public interface IdeaRepository extends JpaRepository<Idea, Long> {
//public interface IdeaRepository extends PagingAndSortingRepository<Idea, Long>{
	
	@Query("SELECT i FROM Idea i WHERE " 
			+ " CONCAT(i.id, '', i.idea_title, '', i.idea_description)"
			+ " LIKE %?1%")
	public Page<Idea> findAll(String keyword, Pageable pageable);

}
