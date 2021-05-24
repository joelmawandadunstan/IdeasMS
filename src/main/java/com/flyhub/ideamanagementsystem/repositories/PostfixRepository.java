package com.flyhub.ideamanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flyhub.ideamanagementsystem.entity.Category;
import com.flyhub.ideamanagementsystem.entity.Gender;
import com.flyhub.ideamanagementsystem.entity.Postfix;

public interface PostfixRepository extends JpaRepository<Postfix,Long> {

}