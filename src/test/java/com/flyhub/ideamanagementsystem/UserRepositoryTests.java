package com.flyhub.ideamanagementsystem;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.flyhub.ideamanagementsystem.entity.Role;
import com.flyhub.ideamanagementsystem.entity.User;
import com.flyhub.ideamanagementsystem.repository.RoleRepository;
import com.flyhub.ideamanagementsystem.repository.UserRepository;

@DataJpaTest 
//@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("mawandajoel20@gmail.com");
		user.setPassword("Joel2021");
		user.setFirstName("Joel");
		user.setLastName("Mawanda");
		
		User savedUser = userRepo.save(user);
		User existUser = entityManager.find(User.class, savedUser.getId());
		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
		
	}
	
	@Test
	public void testFindUserByEmail() {
		String email = "mawandajoel20@gmail.com";
		
		User user = userRepo.findByEmail(email);
		
		assertThat(user).isNotNull();
	}
	
	public void testAddRoleToNewUser() {
		User user = new User();
		user.setEmail("mawandajoel30@gmail.com");
		user.setPassword("Joel2020");
		user.setFirstName("Joel");
		user.setLastName("Mawanda");
		
		Role roleUser = roleRepo.findByName("User");
		user.addRole(roleUser);
		
		User savedUser = userRepo.save(user);
		assertThat(savedUser.getRoles().size()).isEqualTo(1);
	}
	
	@Test
	public void testAddRolesToExistingUser() {
		User user = userRepo.findById(1L).get();
		
		Role roleUser = roleRepo.findByName("User");
		user.addRole(roleUser);
		
		Role roleAdmin = new Role(2L);
		user.addRole(roleAdmin);
		
		User savedUser = userRepo.save(user);
		assertThat(savedUser.getRoles().size()).isEqualTo(2);
	}

}
