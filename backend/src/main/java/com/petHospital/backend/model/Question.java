package com.petHospital.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String askDescription;
	
	private String answer;
	
	private String Adescription;
	
	private String Bdescription;
	
	private String Cdescription;
	
	private String Ddescription;
	
	private int userType;
	
	private Long categoryId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAskDescription() {
		return askDescription;
	}

	public void setAskDescription(String askDescription) {
		this.askDescription = askDescription;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAdescription() {
		return Adescription;
	}

	public void setAdescription(String adescription) {
		Adescription = adescription;
	}

	public String getBdescription() {
		return Bdescription;
	}

	public void setBdescription(String bdescription) {
		Bdescription = bdescription;
	}

	public String getCdescription() {
		return Cdescription;
	}

	public void setCdescription(String cdescription) {
		Cdescription = cdescription;
	}

	public String getDdescription() {
		return Ddescription;
	}

	public void setDdescription(String ddescription) {
		Ddescription = ddescription;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
}
