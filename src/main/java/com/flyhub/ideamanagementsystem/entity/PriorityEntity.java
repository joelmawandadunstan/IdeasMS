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
@Table(name="priority")
public class PriorityEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long priorityId;
	
	private String priorityName;
	
	private String priorityAlias;
	
	private String priorityCode;
	
	private String priorityDescription;
	
    private Date createdOn;

    private Date updatedOn;

    private Long createdBy;

    private Long modifiedBy;

    private Long softDelete;

    private Long hardDelete;

	public PriorityEntity() {
		
	}

//	public PriorityEntity(Long priorityId, String priorityName, String priorityAlias, String priorityCode,
//			String priorityDescription, Date createdOn, Date updatedOn, Long createdBy, Long modifiedBy,
//			Long softDelete, Long hardDelete) {
//		super();
//		this.priorityId = priorityId;
//		this.priorityName = priorityName;
//		this.priorityAlias = priorityAlias;
//		this.priorityCode = priorityCode;
//		this.priorityDescription = priorityDescription;
//		this.createdOn = createdOn;
//		this.updatedOn = updatedOn;
//		this.createdBy = createdBy;
//		this.modifiedBy = modifiedBy;
//		this.softDelete = softDelete;
//		this.hardDelete = hardDelete;
//	} 
}
