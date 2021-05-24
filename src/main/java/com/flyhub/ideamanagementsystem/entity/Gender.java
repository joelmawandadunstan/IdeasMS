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
@Table(name="gender")
public class Gender {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="gender_id")
	private Long id;
	
	@Column(name="gender_name", nullable=false)
	private String genderName;
	
	@Column(name="gender_description", nullable=true)
	private String genderDescription;
	
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

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public String getGenderDescription() {
		return genderDescription;
	}

	public void setGenderDescription(String genderDescription) {
		this.genderDescription = genderDescription;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCreatedByGlobalUserId() {
		return createdByGlobalUserId;
	}

	public void setCreatedByGlobalUserId(Long createdByGlobalUserId) {
		this.createdByGlobalUserId = createdByGlobalUserId;
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
	
}
	

	
	
