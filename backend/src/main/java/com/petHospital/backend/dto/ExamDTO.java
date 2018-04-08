package com.petHospital.backend.dto;


import java.sql.Time;
import java.util.List;

import com.petHospital.backend.model.Category;
import com.petHospital.backend.model.Question;


public class ExamDTO {
	private Long id;
	
	private Time time;
	
	private Category category;
	
	private List<Question> questions;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public Time getTime() {
		return time;
	}
	
	public void setTime(Time time) {
		this.time=time;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}


