package com.petHospital.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Question {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String askDescription;
	
	private String answer;
	
	private int userType;
	
	private int questionType; //CHOICE(0), Filling(1),JUDGEMENT(2),DESCRIPTION(3);
	
	//for choice question
	private String Adescription;
		
	private String Bdescription;

	private String Cdescription;
		
	private String Ddescription;
		
	@ManyToOne   
	@JoinColumn(name="category_id") 
	private Category category;

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}
}
