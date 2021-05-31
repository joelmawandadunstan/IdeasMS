package com.flyhub.ideamanagementsystem.repositories;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.flyhub.ideamanagementsystem.entity.Idea;
import com.flyhub.ideamanagementsystem.entity.User;

@Configuration
public interface UserRepository extends JpaRepository<User, Long> {
//public interface UserRepository extends PagingAndSortingRepository<User, Long>{
	
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
	public User findByVerificationCode(String code);
	
	@Query("SELECT u FROM User u WHERE " 
			+ " CONCAT(u.id, '', u.email, '', u.firstName, '', u.lastName)"
			+ " LIKE %?1%")
	public Page<User> findAll(String keyword, Pageable pageable);
}
