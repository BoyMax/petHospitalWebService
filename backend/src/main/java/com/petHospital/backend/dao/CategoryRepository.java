package com.petHospital.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.petHospital.backend.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{
	
}