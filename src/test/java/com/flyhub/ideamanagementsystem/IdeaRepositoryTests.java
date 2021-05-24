package com.flyhub.ideamanagementsystem;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.flyhub.ideamanagementsystem.entity.Idea;
import com.flyhub.ideamanagementsystem.repositories.IdeaRepository;

@DataJpaTest 
@Rollback(false)

public class IdeaRepositoryTests {
	
	@Autowired
	private IdeaRepository ideaRepo;
	
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateIdea() {
		Idea idea = new Idea();
		idea.setIdea_title("Mobile Parking App");
		idea.setIdea_description("It is an Android Mobile Parking App");
		
		
		Idea savedIdea = ideaRepo.save(idea);
		Idea existIdea = entityManager.find(Idea.class, savedIdea.getId());
		//assertThat(existIdea.getIdea_title()).isEqualTo(idea.getIdea_title());
		assertThat(existIdea.getIdea_title()).isEqualTo(idea.getIdea_title());
	}
	

}
