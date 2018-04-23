package com.petHospital.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.petHospital.backend.model.Medicine;

public interface MedicineRepository extends CrudRepository<Medicine, Long>{
	@Query(value = "select m from Medicine m where m.name like %:name%")  
	List<Medicine> search(@Param("name") String name);
}
