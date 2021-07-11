package com.flyhub.ideamanagementsystem;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import com.flyhub.ideamanagementsystem.entity.PriorityEntity;
import com.flyhub.ideamanagementsystem.repositories.PriorityRepository;

@DataJpaTest 
@Rollback(false)
public class PriorityRepositoryTests {
	@Autowired
	private PriorityRepository priorityRepository;
	
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreatePriority() {
		PriorityEntity priority = new PriorityEntity();
		priority.setPriorityName("High");
		
			
		PriorityEntity savedPriority = priorityRepository.save(priority);
		PriorityEntity existPriority = entityManager.find(PriorityEntity.class, savedPriority.getPriorityId());
		assertThat(existPriority.getPriorityName()).isEqualTo(priority.getPriorityName());
	}
	

}
