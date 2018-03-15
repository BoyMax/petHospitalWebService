package com.petHospital.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.petHospital.backend.model.Department;

public interface DepartmentRepository extends CrudRepository<Department, Long>{
}
