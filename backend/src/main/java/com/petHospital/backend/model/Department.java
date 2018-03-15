package com.petHospital.backend.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Department {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String description;
	
	@ManyToMany(cascade = CascadeType.ALL)  
	@JoinTable(name = "Department_Manager",  
	joinColumns = {@JoinColumn(name = "department_id")},  
	inverseJoinColumns = {@JoinColumn(name = "user_id")})   
	private List<User> managers;
	
	@OneToMany(cascade=CascadeType.ALL)   
	@JoinColumn(name="department_id")//注释的是另一个表指向本表的外键。   
	private List<User> users;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<User> getUsers() {  
	    return users;   
	}  
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	

	public void setManagers(List<User> managers) {
		this.managers = managers;
	}

	public List<User> getManagers() {   
	    return managers;   
	}

}
