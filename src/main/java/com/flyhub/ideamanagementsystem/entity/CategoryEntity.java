package com.flyhub.ideamanagementsystem.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="category")
public class CategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long categoryId;
	
	private String categoryName;
	
	private String categoryAlias;
	
	private String categoryCode;
	
	private String categoryDescription;

	private Date createdOn;

    private Date updatedOn;

    private Long createdBy;

    private Long modifiedBy;

    private Long softDelete;

    private Long hardDelete;
    
    
	public CategoryEntity() {
			
		}
	
//	 public CategoryEntity(Long categoryId, String categoryName, String categoryAlias, String categoryCode,
//				String categoryDescription, Date createdOn, Date updatedOn, Long createdBy, Long modifiedBy,
//				Long softDelete, Long hardDelete) {
//			super();
//			this.categoryId = categoryId;
//			this.categoryName = categoryName;
//			this.categoryAlias = categoryAlias;
//			this.categoryCode = categoryCode;
//			this.categoryDescription = categoryDescription;
//			this.createdOn = createdOn;
//			this.updatedOn = updatedOn;
//			this.createdBy = createdBy;
//			this.modifiedBy = modifiedBy;
//			this.softDelete = softDelete;
//			this.hardDelete = hardDelete;
//		} 
}
