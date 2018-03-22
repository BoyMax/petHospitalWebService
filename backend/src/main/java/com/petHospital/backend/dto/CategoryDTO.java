package com.petHospital.backend.dto;

import java.util.List;

import com.petHospital.backend.model.Illness;

public class CategoryDTO {
	
	private Long id;
	
	private String name;
	
	private List<Illness> illnesses;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Illness> getIllnesses() {
		return illnesses;
	}

	public void setIllnesses(List<Illness> illnesses) {
		this.illnesses = illnesses;
	}
	
}