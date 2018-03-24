package com.petHospital.backend.dto;

import com.petHospital.backend.enumeration.MediaEnum;

public class MultimediaDTO {
	private Long id;
	
	private int type;
	
	private String url;
	
	private int caseType;
	
	public void setType(int type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(MediaEnum type) {
		this.type = type.getIndex();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getCaseType() {
		return caseType;
	}

	public void setCaseType(int caseType) {
		this.caseType = caseType;
	}
}
