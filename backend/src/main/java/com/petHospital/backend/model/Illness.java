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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Illness {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String diseaseDescription;
	
	private String process;
	
	private String treatment;
	
	private String result;
	
	@ManyToOne   
	@JoinColumn(name="category_id")   
	private Category category;
	
	@JsonIgnore
	@ManyToMany(cascade= {CascadeType.REFRESH,CascadeType.DETACH})  
	@JoinTable(name = "Illness_Multimedia",  
	joinColumns = {@JoinColumn(name = "illness_id")},  
	inverseJoinColumns = {@JoinColumn(name = "multimedia_id")})   
	private List<Multimedia> multimedias;
	
	@JsonIgnore
	@ManyToMany(cascade= {CascadeType.REFRESH,CascadeType.DETACH})  
	@JoinTable(name = "Illness_Medicine",  
	joinColumns = {@JoinColumn(name = "illness_id")},  
	inverseJoinColumns = {@JoinColumn(name = "medicine_id")})   
	private List<Medicine> medicines;

	@JsonIgnore
	@OneToMany(cascade= {CascadeType.REFRESH,CascadeType.DETACH})   
	@JoinColumn(name="illness_id")//注释的是另一个表指向本表的外键。   
	private List<Vaccine> vaccines;
	
	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	public String getDiseaseDescription() {
		return diseaseDescription;
	}

	public void setDiseaseDescription(String diseaseDescription) {
		this.diseaseDescription = diseaseDescription;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

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
	
	public List<Multimedia> getMultimedias() {
		return multimedias;
	}

	public void setMultimedias(List<Multimedia> multimedias) {
		this.multimedias = multimedias;
	}
	
	public List<Vaccine> getVaccines() {
		return vaccines;
	}

	public void setVaccines(List<Vaccine> vaccines) {
		this.vaccines = vaccines;
	}
}
