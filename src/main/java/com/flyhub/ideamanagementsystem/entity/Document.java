package com.flyhub.ideamanagementsystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "documents")
public class Document {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	 @ManyToOne//	  
	  @JoinColumn(name = "idea_id", referencedColumnName="idea_id", insertable=false, updatable=false)
	 private Idea idea;
	 private Long idea_id;
	
	@Column(length = 512, nullable = false, unique = true)
	private String name;
	
	private long size;
	
	@Column(name = "upload_time")
	private Date uploadTime;
	
	private byte[] content;
	
	

	public Idea getIdea() {
		return idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}

	public Long getIdea_id() {
		return idea_id;
	}

	public void setIdea_id(Long idea_id) {
		this.idea_id = idea_id;
	}

	public Document(Long id, String name, long size) {
		this.id = id;
		this.name = name;
		this.size = size;
	}

	public Document() {
		
	}

	//This is from the Article
	public Document(String fileName, String contentType, byte[] bytes) {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	
	
}
