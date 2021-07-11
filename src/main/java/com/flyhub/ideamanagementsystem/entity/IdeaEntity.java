package com.flyhub.ideamanagementsystem.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

 	@Data
	@Entity
	@Table(name = "ideas")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
	public class IdeaEntity {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "ideaId")
		private Long ideaId;
		
		@Column(name = "ideaTitle", nullable = false, unique = true, length = 45)
		private String ideaTitle;
		
		@Column(name = "ideaDescription", nullable = false, length = 64)
		private String ideaDescription;
		
		@Column(name = "ideaBackground", nullable = false, length = 64)
		private String ideaBackground;
		
		@Column(name="categoryId")
		private int categoryId;
		
		@Column(name="priorityId")
		private int priorityId;
		
		@Column(name="createDate")
		private String createDate;
		
		@Column(name="createdBy")
		private String createdBy;
		
		@Column(name="modifiedBy")
		private String modifiedBy;
		
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "idea")
		@OnDelete(action = OnDeleteAction.CASCADE)
		private List<AttachmentEntity> attachment;	
	}
	
