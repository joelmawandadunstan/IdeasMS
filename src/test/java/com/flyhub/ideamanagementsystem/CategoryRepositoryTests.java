package com.flyhub.ideamanagementsystem;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import com.flyhub.ideamanagementsystem.entity.CategoryEntity;
import com.flyhub.ideamanagementsystem.repositories.CategoryRepository;


@DataJpaTest 
@Rollback(false)
public class CategoryRepositoryTests {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateCategory() {
		CategoryEntity category = new CategoryEntity();
		category.setCategoryName("Technology");
		
			
		CategoryEntity savedCategory = categoryRepository.save(category);
		CategoryEntity existCategory = entityManager.find(CategoryEntity.class, savedCategory.getCategoryId());
		assertThat(existCategory.getCategoryName()).isEqualTo(category.getCategoryName());
	}
	

}
