package com.petHospital.backend.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Category {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;

	@OneToMany(cascade= {CascadeType.REFRESH,CascadeType.DETACH})
	@JoinColumn(name="category_id")//注释的是另一个表指向本表的外键。 
	private List<Illness> illnesses;
	
	@OneToMany(cascade= {CascadeType.REFRESH,CascadeType.DETACH})   
	@JoinColumn(name="category_id")//注释的是另一个表指向本表的外键。   
	private List<Question> questions;

	@OneToMany(cascade= {CascadeType.REFRESH,CascadeType.DETACH})   
	@JoinColumn(name="category_id")//注释的是另一个表指向本表的外键。   
	private List<Exam> exams;
	
	public Long getId() {
		return id;
	}

	public List<Illness> getIllnesses() {
		return illnesses;
	}

	public void setIllnesses(List<Illness> illnesses) {
		this.illnesses = illnesses;
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
	
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}
}
