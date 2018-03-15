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

@Entity
public class Exam {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Time time;
	
	@ManyToOne   
	@JoinColumn(name="category_id") 
	private Category category;
	
	@ManyToMany(cascade = CascadeType.ALL)  
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
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

}
