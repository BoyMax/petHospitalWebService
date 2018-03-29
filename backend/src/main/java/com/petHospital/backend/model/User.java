package com.petHospital.backend.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true, nullable = false)
	private String name;
	
	private int role;   //ADMIN(0), RECEPTION(1),VETERINARIAN(2),ASSISTANT(3);
	
	@Column(nullable = false)
	private String password;
	
	private int status;  //ENABLE(0), DISABLE(1),DELETE(2);
	
	@ManyToOne(cascade= {CascadeType.REFRESH,CascadeType.DETACH}) 
	@JoinColumn(name="department_id")  
	@JsonIgnore
	private Department department;

	@JsonIgnore
	@ManyToMany(mappedBy = "managers") 
	private List<Department> departments;
	
	public Department getDepartment() {   
	    return department;   
	}  
	
	public void setDepartment(Department department) {
		this.department = department;
	}


	public void setRole(int role) {
		this.role = role;
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

	public int getRole() {
		return role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
}
