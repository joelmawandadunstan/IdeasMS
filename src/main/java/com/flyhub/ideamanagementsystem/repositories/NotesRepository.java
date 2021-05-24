package com.flyhub.ideamanagementsystem.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.flyhub.ideamanagementsystem.entity.Notes;
import com.flyhub.ideamanagementsystem.entity.User;

@Repository
//public interface NotesRepository extends JpaRepository<Notes, Long> {
public interface NotesRepository extends PagingAndSortingRepository<Notes, Long>{
	
	@Query("SELECT n FROM Notes n WHERE " 
			+ " CONCAT(n.id, '', n.content)"
			+ " LIKE %?1%")
	public Page<Notes> findAll(String keyword, Pageable pageable);

}
