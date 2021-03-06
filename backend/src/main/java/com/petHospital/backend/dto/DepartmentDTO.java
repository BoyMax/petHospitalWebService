package com.petHospital.backend.dto;

import java.util.List;

import com.petHospital.backend.model.User;

public class DepartmentDTO {

	private Long id;
	
	private String name;
	
	private String description;

	private List<User> managers;

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

	public List<User> getManagers() {
		return managers;
	}

	public void setManagers(List<User> managers) {
		this.managers = managers;
	}
	
}
