package com.flyhub.ideamanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.flyhub.ideamanagementsystem.entity.Priority;

public interface PriorityRepository extends JpaRepository<Priority,Long> {

}
