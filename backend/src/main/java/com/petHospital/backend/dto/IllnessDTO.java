package com.petHospital.backend.dto;

import java.util.List;

import com.petHospital.backend.model.Category;
import com.petHospital.backend.model.Medicine;
import com.petHospital.backend.model.Multimedia;
import com.petHospital.backend.model.Vaccine;

public class IllnessDTO {

private Long id;
	
	private String name;
	
	private String diseaseDescription;
	
	private String process;
	
	private String treatment;
	
	private String result;

	private Category category;
	
	private List<Multimedia> multimedias;
	
	private List<Medicine> medicines;

	private List<Vaccine> vaccines;

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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Multimedia> getMultimedias() {
		return multimedias;
	}

	public void setMultimedias(List<Multimedia> multimedias) {
		this.multimedias = multimedias;
	}

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	public List<Vaccine> getVaccines() {
		return vaccines;
	}

	public void setVaccines(List<Vaccine> vaccines) {
		this.vaccines = vaccines;
	}
	
	
}
