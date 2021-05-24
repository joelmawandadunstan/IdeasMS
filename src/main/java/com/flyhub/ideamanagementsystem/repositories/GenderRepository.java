package com.flyhub.ideamanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flyhub.ideamanagementsystem.entity.Category;
import com.flyhub.ideamanagementsystem.entity.Gender;

public interface GenderRepository extends JpaRepository<Gender,Long> {

}