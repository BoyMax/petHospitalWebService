package com.petHospital.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.petHospital.backend.model.Department;

public interface DepartmentRepository extends CrudRepository<Department, Long>{
	@Query(value = "select d from Department d where d.name like %:name%")  
	List<Department> search(@Param("name") String name);
}
