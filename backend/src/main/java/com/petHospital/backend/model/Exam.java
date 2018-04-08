package com.petHospital.backend.model;

import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Exam {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Time time;
	
	@ManyToOne   
	@JoinColumn(name="category_id") 
	@JsonIgnore
	private Category category;
	
	@ManyToMany(cascade =  {CascadeType.REFRESH,CascadeType.DETACH})  
	@JoinTable(name = "Exam_Question",  
	joinColumns = {@JoinColumn(name = "exam_id")},  
	inverseJoinColumns = {@JoinColumn(name = "question_id")})   
	private List<Question> questions;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Time getTime() {
		return this.time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	
	//自己创建的方法
	public void setCategory(Category category) {
		this.category=category;
	}
	
	public Category getCategory() {
    	return this.category;
	}

	public List<Question> getQuestion() {
		return this.questions;
	}
	
	public void setQuestion(List<Question> questions)
	{
		this.questions=questions;
	}



}
