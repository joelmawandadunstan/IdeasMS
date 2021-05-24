package com.flyhub.ideamanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flyhub.ideamanagementsystem.entity.Category;
import com.flyhub.ideamanagementsystem.entity.Gender;
import com.flyhub.ideamanagementsystem.entity.Postfix;
import com.flyhub.ideamanagementsystem.entity.Prefix;
import com.flyhub.ideamanagementsystem.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile,Long> {

}