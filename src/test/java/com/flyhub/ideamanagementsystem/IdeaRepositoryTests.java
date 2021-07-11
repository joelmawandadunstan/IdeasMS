package com.flyhub.ideamanagementsystem;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import com.flyhub.ideamanagementsystem.entity.IdeaEntity;
import com.flyhub.ideamanagementsystem.repositories.IdeaRepository;

//@SpringBootTest
@DataJpaTest 
@Rollback(false)
public class IdeaRepositoryTests {
	
	@Autowired
	private IdeaRepository ideaRepository;
	
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateIdea() {
		IdeaEntity idea = new IdeaEntity();
		idea.setIdeaTitle("Mobile Parking App");
		idea.setIdeaDescription("It is an Android Mobile Parking App");
		idea.setIdeaBackground("Driver's Parking");
			
		IdeaEntity savedIdea = ideaRepository.save(idea);
		IdeaEntity existIdea = entityManager.find(IdeaEntity.class, savedIdea.getIdeaId());
		assertThat(existIdea.getIdeaTitle()).isEqualTo(idea.getIdeaTitle());
	}
	

}
