package com.petHospital.backend.service;

import com.petHospital.backend.dto.DepartmentDTO;

public interface DepartmentService {
	public DepartmentDTO retreiveDepartment(Long id);
	public DepartmentDTO createDepartment(DepartmentDTO user);
	public DepartmentDTO deleteDepartment(Long id);
	public DepartmentDTO editDepartment(DepartmentDTO user);
}
