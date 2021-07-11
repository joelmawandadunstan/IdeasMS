package com.flyhub.ideamanagementsystem;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import com.flyhub.ideamanagementsystem.entity.AttachmentEntity;
import com.flyhub.ideamanagementsystem.repositories.AttachmentRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AttachmentRepositoryTests {
	
	@Autowired
	private AttachmentRepository attachmentRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	@Rollback(false)
	public void testInsertAttachment() throws IOException {
		File file = new File("C:\\Users\\A241902\\documents\\FlyHub.docx");
		
		AttachmentEntity attachment = new AttachmentEntity();
		attachment.setName(file.getName());
		
		byte[] bytes = Files.readAllBytes(file.toPath());
		attachment.setContent(bytes);
		long fileSize = bytes.length;
		attachment.setSize(fileSize);
		attachment.setUploadTime(new Date());
		
		AttachmentEntity savedDoc = attachmentRepository.save(attachment);
		
		AttachmentEntity existDoc = entityManager.find(AttachmentEntity.class, savedDoc.getAttachmentId());
		
		assertThat(existDoc.getSize()).isEqualTo(fileSize);
	}
	

}
