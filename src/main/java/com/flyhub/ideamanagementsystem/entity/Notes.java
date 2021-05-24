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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "notes")
public class Notes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notes_id")
	private Long id;
	
	 @ManyToOne//	  
	  @JoinColumn(name = "idea_id", referencedColumnName="idea_id", insertable=false, updatable=false)
	 private Idea idea;
	 private Long idea_id;
//	  
//	  @OnDelete(action = OnDeleteAction.CASCADE)
	 
	 @Column(name = "content", nullable = false, length = 1000)
	 private String content;
	
	
	 @Column(name = "create_date", nullable = true)

	 @Temporal(TemporalType.TIMESTAMP)
	 private Date createDate; // @ManyToOne

	 

	public Idea getIdea() {
		return idea;
	}


	public void setIdea(Idea idea) {
		this.idea = idea;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	  
	  public Long getIdea_id() {
		return idea_id;
	}


	public void setIdea_id(Long idea_id) {
		this.idea_id = idea_id;
	}


	public Notes() {
		super();
	}


//	public Notes(Idea idea, String content) {
//		super();
//		this.idea = idea;
//		this.content = content;
//	}
//
//
//	public Long getIdea_id() {
//		return idea_id;
//	}
//
//
//	public void setIdea_id(Long idea_id) {
//		this.idea_id = idea_id;
//	}
	
	
}
