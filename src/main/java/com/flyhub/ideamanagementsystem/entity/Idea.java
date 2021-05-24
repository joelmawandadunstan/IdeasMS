package com.flyhub.ideamanagementsystem.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


	@Entity
	@Table(name = "ideas")
	public class Idea {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "idea_id")
		private Long id;
		
		 @ManyToOne//	  
		  @JoinColumn(name = "user_id", referencedColumnName="user_id", insertable=false, updatable=false)
		 private User user;
		 private Long user_id;
		
		@Column(name = "idea_title", nullable = false, unique = true, length = 45)
		private String idea_title;
		
		@Column(name = "idea_description", nullable = false, length = 64)
		private String idea_description;
		
		@Column(name="category_id")
		private int categoryId;
		
		//priority
		@Column(name="priority_id")
		private int priorityId;
		
		@Column(name="createDate")
		private String createDate;
		
		@OneToMany(mappedBy = "idea", orphanRemoval=true)
		private List<Notes> notes;
		
		@OneToMany(mappedBy = "idea", orphanRemoval=true)
		private List<Document> document;
		
		
	
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Long getUser_id() {
			return user_id;
		}

		public void setUser_id(Long user_id) {
			this.user_id = user_id;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public int getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(int categoryId) {
			this.categoryId = categoryId;
		}
		
		public int getPriorityId() {
			return priorityId;
		}

		public void setPriorityId(int priorityId) {
			this.priorityId = priorityId;
		}

		public String getIdea_title() {
			return idea_title;
		}

		public void setIdea_title(String idea_title) {
			this.idea_title = idea_title;
		}

		public String getIdea_description() {
			return idea_description;
		}

		public void setIdea_description(String idea_description) {
			this.idea_description = idea_description;
		}

		public String getCreateDate() {
			return createDate;
		}

		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}
		
		
	}
	
