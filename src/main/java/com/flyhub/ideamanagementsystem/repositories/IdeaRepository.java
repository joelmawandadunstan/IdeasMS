package com.flyhub.ideamanagementsystem.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flyhub.ideamanagementsystem.entity.IdeaEntity;

@Repository
public interface IdeaRepository extends JpaRepository<IdeaEntity, Long> {

	@Query("SELECT i FROM IdeaEntity i WHERE " + " CONCAT(i.ideaId, '', i.ideaTitle, '', i.ideaDescription)" + " LIKE %?1%")
	public List<IdeaEntity> findAll();

}
