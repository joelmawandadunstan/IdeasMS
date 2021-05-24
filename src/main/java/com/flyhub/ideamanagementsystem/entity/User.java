package com.flyhub.ideamanagementsystem.entity;

import java.util.HashSet;
import java.util.List;

import javax.persistence.JoinColumn;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;

	@Column(nullable = false, unique = true, length = 45)
	private String email;

	@Column(nullable = false, length = 64)
	private String password;
	
	
	@Column(name = "first_name", nullable = false, length = 45)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 45)
	private String lastName;
	
	@Column(name="gender_id")
	private int genderId;
	
	@Column(name="postfix_id")
	private int postfixId;
	
	@Column(name="prefix_id")
	private int prefixId;

	@Column(name = "verification_code")
	private String verificationCode;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@OneToMany(mappedBy = "user", orphanRemoval=false)
	private List<Idea> idea;

	
	//constructor
	public User() {
			
		}
	
	// getters and setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getGenderId() {
		return genderId;
	}

	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}
	
	public int getPostfixId() {
		return postfixId;
	}

	public void setPostfixId(int postfixId) {
		this.postfixId = postfixId;
	}
	
	public int getPrefixId() {
		return prefixId;
	}

	public void setPrefixId(int prefixId) {
		this.prefixId = prefixId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}
	
//	public User(String email, String password, String firstName, String lastName, Set<Role> roles) {
//		super();
//		this.email = email;
//		this.password = password;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.roles = roles;
//	}

	
	
	
	

	

	

	

//	@Override
//	public String toString() {
//		return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
//				+ ", lastName=" + lastName + ", verificationCode=" + verificationCode + ", roles=" + roles + "]";
//	}
//	
	
}
