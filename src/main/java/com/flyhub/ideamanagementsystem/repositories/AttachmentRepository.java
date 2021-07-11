package com.flyhub.ideamanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.flyhub.ideamanagementsystem.entity.AttachmentEntity;

@Repository
public interface AttachmentRepository extends JpaRepository<AttachmentEntity, Long> {
//
//	@Query("SELECT new Attachment(d.attachmentId, d.name, d.size) FROM Attachment d ORDER BY d.uploadTime DESC")
//	List<AttachmentEntity> findAll();

}
