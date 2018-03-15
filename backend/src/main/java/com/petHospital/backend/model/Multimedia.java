package com.petHospital.backend.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.petHospital.backend.enumeration.MediaEnum;

@Entity
public class Multimedia {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private int type;
	
	private int url;

	@ManyToMany(cascade=CascadeType.REFRESH,mappedBy="multimedias")
    private List<Illness> illnesses;
    
	public List<Illness> getIllnesses() {
		return illnesses;
	}

	public void setIllnesses(List<Illness> illnesses) {
		this.illnesses = illnesses;
	}

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

	public int getUrl() {
		return url;
	}

	public void setUrl(int url) {
		this.url = url;
	}
}
