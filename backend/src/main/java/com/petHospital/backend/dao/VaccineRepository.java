package com.petHospital.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.petHospital.backend.model.Vaccine;

public interface VaccineRepository extends CrudRepository<Vaccine, Long>{
	
	@Query(value = "select v from Vaccine v where v.name like %:name%")  
	List<Vaccine> search(@Param("name") String name);
}
