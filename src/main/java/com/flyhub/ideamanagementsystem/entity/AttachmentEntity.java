package com.flyhub.ideamanagementsystem.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Attachments")
public class AttachmentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attachmentId;
	 
	 @ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name="ideaId", insertable=false,updatable=false)
	    private IdeaEntity idea;
	    private Long ideaId;
	
	@Column(length = 512, nullable = false, unique = true)
	private String name;
	
	private long size;
	
	@Column(name = "upload_time")
	private Date uploadTime;
	
	private byte[] content;
}
