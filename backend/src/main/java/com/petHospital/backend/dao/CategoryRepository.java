package com.petHospital.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.petHospital.backend.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{
	@Query(value = "select c from Category c where c.name like %:name%")  
	List<Category> search(@Param("name") String name);
	
	
}