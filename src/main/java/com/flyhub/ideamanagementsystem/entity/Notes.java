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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "notes")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Notes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notes_id")
	private Long id;
	
	// @ManyToOne	  
//	  @JoinColumn(name = "idea_id", insertable=false, updatable=false)
//	 private Idea idea;
//	 private Long idea_id;
//	  
//	  @OnDelete(action = OnDeleteAction.CASCADE)
	 
	@JsonIgnore
	 @ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name="idea_id", insertable=false,updatable=false)
	    private Idea idea;
	    private Long idea_id;
	 
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


	public Notes() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdea_id() {
		return idea_id;
	}

	public void setIdea_id(Long idea_id) {
		this.idea_id = idea_id;
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

	
}
