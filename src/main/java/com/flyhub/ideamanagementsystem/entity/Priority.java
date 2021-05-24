package com.flyhub.ideamanagementsystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
@Entity
@Table(name="priority")
public class Priority {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="priority_id")
	private Long id;
	
	@Column(name="priority_name", nullable=false)
	private String priorityName;
	
	@Column(name="priority_description", nullable=true)
	private String priorityDescription;
	
	@Column(name="create_date", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	//@ManyToOne
	@Column(name ="created_by_global_use_id",nullable=true)
	private Long createdByGlobalUserId;	
	
	//private User user;
	@Column(name="modified_date", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	
	@Column(name="modified_by_global_user_id", nullable=true)
	private Long modifiedByGlobalUserId;
	
	@Column(name="tenant_id", nullable=true)
	private Long tenantId;
	
	@Column(name="modified_reason",nullable=true)
	private String modifiedReason;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPriorityName() {
		return priorityName;
	}
	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}
	public String getProrityDescription() {
		return priorityDescription;
	}
	public void setPriorityDescription(String priorityDescription) {
		this.priorityDescription = priorityDescription;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}	
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Long getModifiedByGlobalUserId() {
		return modifiedByGlobalUserId;
	}
	public void setModifiedByGlobalUserId(Long modifiedByGlobalUserId) {
		this.modifiedByGlobalUserId = modifiedByGlobalUserId;
	}
	public Long getTenantId() {
		return tenantId;
	}
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}
	public String getModifiedReason() {
		return modifiedReason;
	}
	public void setModifiedReason(String modifiedReason) {
		this.modifiedReason = modifiedReason;
	}
	public Long getCreatedByGlobalUserId() {
		return createdByGlobalUserId;
	}
	public void setCreatedByGlobalUserId(Long createdByGlobalUserId) {
		this.createdByGlobalUserId = createdByGlobalUserId;
	}
	
}
