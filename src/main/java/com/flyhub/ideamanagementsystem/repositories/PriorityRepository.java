package com.flyhub.ideamanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.flyhub.ideamanagementsystem.entity.PriorityEntity;

@Repository
public interface PriorityRepository extends JpaRepository<PriorityEntity, Long>{

}