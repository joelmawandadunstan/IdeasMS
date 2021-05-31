package com.flyhub.ideamanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flyhub.ideamanagementsystem.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
