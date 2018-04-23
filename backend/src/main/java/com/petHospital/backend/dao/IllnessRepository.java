package com.petHospital.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.petHospital.backend.model.Illness;

public interface IllnessRepository extends CrudRepository<Illness, Long>{
//	public Illness getIllnessByName(String illnessName);
	
	@Query(value = "select i from Illness i where  i.name like %:name%")  
    List<Illness> searchIllness(@Param("name") String name);
}