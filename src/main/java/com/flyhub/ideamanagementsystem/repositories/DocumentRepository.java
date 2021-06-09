package com.flyhub.ideamanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.flyhub.ideamanagementsystem.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
	
//	@Query("SELECT new Document(d.id, d.name, d.size) FROM document d ORDER BY d.uploadTime DESC")
//	List<Document> findAll();

}
